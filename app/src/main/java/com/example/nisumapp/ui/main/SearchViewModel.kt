package com.example.nisumapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.nisumapp.models.Song
import com.example.nisumapp.repos.NetworkState
import com.example.nisumapp.repos.SearchRepository
import com.example.nisumapp.ui.main.datasource.SongDataSource
import com.example.nisumapp.ui.main.datasource.SongDataSourceFactory


class SearchViewModelFactory(var repository: SearchRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = SearchViewModel( repository ) as T
}


class SearchViewModel(var repository: SearchRepository) : ViewModel() {

    private val songDataSource = SongDataSourceFactory(repository)
    val songsList = LivePagedListBuilder<Int, Song>(songDataSource, pagedListConfig()).build()
    val networkState: LiveData<NetworkState> = (songDataSource.getSongSource() as SongDataSource).networkState


    fun fetchSongsByTerm(term: String) {
        val search = term.trim()
        if (songDataSource.getCurrentTerm() == search) return
        songDataSource.updateTerm(term)
    }

    private fun pagedListConfig() = PagedList.Config.Builder()
        .setInitialLoadSizeHint(5)
        .setEnablePlaceholders(false)
        .setPageSize(20)
        .build()

}