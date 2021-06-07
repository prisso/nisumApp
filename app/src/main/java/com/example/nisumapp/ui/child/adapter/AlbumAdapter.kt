package com.example.nisumapp.ui.child.adapter

import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nisumapp.databinding.SearchItemBinding
import com.example.nisumapp.databinding.SongItemBinding
import com.example.nisumapp.models.Song
import com.example.nisumapp.ui.main.adapter.SongItemAdapter


class AlbumAdapter: RecyclerView.Adapter<AlbumAdapter.SongAlbumViewHolder>() {

    private var songList = emptyList<Song>()

    fun setList(list: List<Song>) {
        songList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongAlbumViewHolder {
        val binding = SongItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SongAlbumViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: SongAlbumViewHolder, position: Int) {
        with(holder) {
            bindTo(songList[position])
        }
    }

    override fun getItemCount(): Int = songList.size

    inner class SongAlbumViewHolder(val root: View): RecyclerView.ViewHolder(root) {
        private lateinit var mediaPlayer: MediaPlayer

        fun bindTo(song: Song?){
            song?.let {
                with(SongItemBinding.bind(root)) {
                    tvSongTitle.text = it.trackName
                    tvAdvance.text = "-- : --"

                    mediaPlayer = MediaPlayer().apply {
                        setAudioStreamType(AudioManager.STREAM_MUSIC)
                        setDataSource(root.context, Uri.parse(song.previewUrl))
                        setOnTimedTextListener { mp, text ->
                            tvAdvance.text = text.text
                        }
                        prepare()
                    }

                    root.setOnClickListener {
                        if (mediaPlayer.isPlaying)
                            mediaPlayer.stop()
                        else
                            mediaPlayer.start()
                    }
                }
            }
        }
    }
}