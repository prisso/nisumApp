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


class AlbumViewModelFactory(var repository: SearchRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = AlbumViewModel( repository ) as T
}


class AlbumViewModel(var repository: SearchRepository) : ViewModel() {
    private val _songAlbumList = MutableLiveData<List<Song>>()
    val songList: LiveData<List<Song>> = _songAlbumList


    fun makeAlbumFromCollection(id: Int) {
        if (repository.getCurrentSongList().isEmpty()) return

        CoroutineScope( Dispatchers.Default ).async {
            val list = repository.getCurrentSongList().filter { it.collectionId == id }.sortedBy { it.trackId }
            _songAlbumList.postValue( list )
        }
    }
}
