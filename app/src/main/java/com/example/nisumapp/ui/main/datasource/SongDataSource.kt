package com.example.nisumapp.ui.main.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.nisumapp.models.Song
import com.example.nisumapp.repos.NetworkState
import com.example.nisumapp.repos.SearchRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SongDataSource(private val repository: SearchRepository, val searchTerm: String):
    PageKeyedDataSource<Int, Song>() {

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState> = _networkState

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Song>) {
        _networkState.postValue(NetworkState.RUNNING)
        CoroutineScope(Dispatchers.Default).launch {
            val page = params.key
            val songList = repository.getCurrentSongList()
            val newPage = if (songList.size >= (page+1) * params.requestedLoadSize)
                songList.slice( IntRange((page * params.requestedLoadSize), ((page+1) * params.requestedLoadSize)) )
            else
                songList.takeLast( songList.size - (page * params.requestedLoadSize) )
            _networkState.postValue(NetworkState.SUCCESS)
            callback.onResult(newPage, page+1)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Song>) {
        //
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Song>) {
        _networkState.postValue(NetworkState.RUNNING)
        CoroutineScope(Dispatchers.Default).launch( getJobErrorHandler() ) {
            val result = repository.searchFor( searchTerm )
            val firstPage = result.take(params.requestedLoadSize)
            _networkState.postValue(NetworkState.SUCCESS)
            callback.onResult(firstPage, 0, firstPage.size)
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        _networkState.postValue(NetworkState.FAILED)
    }
}