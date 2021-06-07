package com.example.nisumapp.ui.child

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.nisumapp.MainActivity
import com.example.nisumapp.databinding.AlbumFragmentBinding
import com.example.nisumapp.ui.child.adapter.AlbumAdapter


class AlbumFragment : Fragment() {

    companion object {
        fun newInstance() = AlbumFragment()
    }

    private val viewModel: AlbumViewModel by viewModels { AlbumViewModelFactory( (activity as MainActivity).getRepository() )  }
    private val args: AlbumFragmentArgs by navArgs()
    private lateinit var binding: AlbumFragmentBinding
    private lateinit var adapter: AlbumAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AlbumFragmentBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configRecyclerView()
        configObservables()

        viewModel.makeAlbumFromCollection( args.collectionId )
    }

    private fun configRecyclerView() {
        adapter = AlbumAdapter()
        binding.rvAlbumList.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvAlbumList.adapter = this.adapter
    }

    private fun configObservables() {
        viewModel.buildAlbumState.observe(viewLifecycleOwner, { state ->
            if (state == null) return@observe
            when(state) {
                BuildAlbumState.FINISHED -> binding.progressBar.isVisible = false
                BuildAlbumState.MAKING -> binding.progressBar.isVisible = true
            }
        })
        viewModel.viewState.observe(viewLifecycleOwner, { viewState ->
            when(viewState) {
                is AlbumViewState.artworkUpdated -> loadImage( viewState.url )
                is AlbumViewState.albumTitleUpdated -> binding.tvAlbumName.text = viewState.title
                is AlbumViewState.bandNameUpdated -> binding.tvBandName.text = viewState.name
            }
        })
        viewModel.songList.observe(viewLifecycleOwner, {
            viewModel.loadInfo()
            adapter.setList( it )
        })
    }

    private fun loadImage(url: String) {
        Glide.with(this.requireContext())
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(RequestOptions.circleCropTransform())
            .into( binding.ivAlbumCover )
    }
}