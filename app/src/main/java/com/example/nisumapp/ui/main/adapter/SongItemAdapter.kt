package com.example.nisumapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.nisumapp.databinding.SearchItemBinding
import com.example.nisumapp.models.Song


class SongItemAdapter(private val callback: OnClickListener): PagedListAdapter<Song, RecyclerView.ViewHolder>(
    diffCallback) {

    interface OnClickListener {
        fun onClickItemWithCollectionId(id: Int)
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Song>() {
            override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean = oldItem == newItem
            override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean = oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder as ItemViewHolder) {
            bindTo( getItem(position) )
            getItem(position)?.let { song ->
                this.root.setOnClickListener {
                    callback.onClickItemWithCollectionId( song.collectionId )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context))
        return ItemViewHolder(binding.root)
    }

    inner class ItemViewHolder(val root: View): RecyclerView.ViewHolder(root) {

        fun bindTo(song: Song?){
            song?.let {
                with(SearchItemBinding.bind(root)) {
                    loadImage(it.artworkUrl, ivAlbumCover)
                    tvSongTitle.text = it.trackName
                    tvBandName.text = it.artistName
                }
            }
        }

        private fun loadImage(url: String, imageView: ImageView) {
            Glide.with(itemView.context)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(RequestOptions.circleCropTransform())
                .into(imageView)
        }
    }
}