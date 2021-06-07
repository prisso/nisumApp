package com.example.nisumapp.ui.main

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nisumapp.MainActivity
import com.example.nisumapp.databinding.SearchFragmentBinding
import com.example.nisumapp.repos.NetworkState
import com.example.nisumapp.ui.main.adapter.SongItemAdapter


class SearchFragment : Fragment(), SongItemAdapter.OnClickListener {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private val viewModel: SearchViewModel by viewModels{ SearchViewModelFactory( (activity as MainActivity).getRepository() ) }
    private lateinit var binding: SearchFragmentBinding
    private lateinit var adapter: SongItemAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = SearchFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressBar.isVisible = false
        binding.editSearchTermEdit.setText("in utero")

        viewModel.init()

        configRecyclerView()
        configObservables()
        configEditText()
    }

    private fun configRecyclerView() {
        adapter = SongItemAdapter(this)
        binding.rvSongsList.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        binding.rvSongsList.adapter = this.adapter
    }

    private fun configObservables() {
        viewModel.networkState.observe(viewLifecycleOwner, Observer { netState ->
            if (netState == null) return@Observer
            when(netState) {
                NetworkState.RUNNING -> binding.progressBar.isVisible = true
                NetworkState.SUCCESS -> binding.progressBar.isVisible = false
                NetworkState.FAILED -> {
                    binding.progressBar.isVisible = false
                    Toast.makeText(this.context, "Failed to get info ...", Toast.LENGTH_LONG).show()
                }
            }
        })

        viewModel.songsList.observe(viewLifecycleOwner, Observer { pagedList ->
            if (pagedList == null) return@Observer

            if (this::adapter.isInitialized) {
                adapter.submitList( pagedList )
            }
        })
    }

    private fun configEditText() {
        binding.editSearchTermEdit.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
                if ((event.action == KeyEvent.ACTION_DOWN) &&
                    (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        hideKeyboard()
                        viewModel.fetchSongsByTerm( binding.editSearchTermEdit.text.toString() )
                        return true;
                }
                return false;
            }
        })
    }

    private fun hideKeyboard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(this.binding.root.windowToken, 0)
    }

    override fun onClickItemWithCollectionId(id: Int) {
        val action = SearchFragmentDirections.actionSearchFragmentToAlbumFragment( id )
        findNavController().navigate( action )
    }
}