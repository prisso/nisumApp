package com.example.nisumapp.ui.main.datasource

import androidx.paging.DataSource
import com.example.nisumapp.models.Song
import com.example.nisumapp.repos.SearchRepository


class SongDataSourceFactory(val repository: SearchRepository, var searchTerm: String = ""):
    DataSource.Factory<Int, Song>() {

    private lateinit var songDataSource: DataSource<Int, Song>

    override fun create(): DataSource<Int, Song> {
        songDataSource = SongDataSource(repository, searchTerm)
        return  songDataSource
    }

    fun getCurrentTerm() = searchTerm

    fun getSongSource() = songDataSource

    fun updateTerm(term: String) {
        this.searchTerm = term
        songDataSource.invalidate()
    }
}