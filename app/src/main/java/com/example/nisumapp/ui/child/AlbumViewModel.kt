package com.example.nisumapp.ui.child

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nisumapp.models.Song
import com.example.nisumapp.repos.SearchRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class AlbumViewModelFactory(var repository: SearchRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = AlbumViewModel( repository ) as T
}


class AlbumViewModel(var repository: SearchRepository) : ViewModel() {
    private val _songAlbumList = MutableLiveData<List<Song>>()
    val songList: LiveData<List<Song>> = _songAlbumList

    private val _viewState = MutableLiveData<AlbumViewState>()
    val viewState: LiveData<AlbumViewState> = _viewState


    fun loadInfo() {
    }

    fun makeAlbumFromCollection(id: Int) {
        if (repository.getCurrentSongList().isEmpty()) return

        CoroutineScope( Dispatchers.Default ).launch {
            val list = repository.getCurrentSongList().filter { it.collectionId == id }.sortedBy { it.trackId }
            _songAlbumList.postValue( list )
        }
    }
}
