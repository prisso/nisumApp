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


enum class BuildAlbumState {
    MAKING,
    FINISHED
}


class AlbumViewModel(var repository: SearchRepository) : ViewModel() {
    private val _songAlbumList = MutableLiveData<List<Song>>()
    val songList: LiveData<List<Song>> = _songAlbumList

    private val _viewState = MutableLiveData<AlbumViewState>()
    val viewState: LiveData<AlbumViewState> = _viewState

    private val _buildingState = MutableLiveData<BuildAlbumState>()
    val buildAlbumState: LiveData<BuildAlbumState> = _buildingState

    fun loadInfo() {
        _songAlbumList.value?.get(0)?.let { song ->
            with(_viewState) {
                value = AlbumViewState.albumTitleUpdated( song.collectionName )
                value = AlbumViewState.bandNameUpdated( song.artistName )
                value = AlbumViewState.artworkUpdated( song.artworkUrl )
            }
        }
    }

    fun makeAlbumFromCollection(id: Int) {
        if (repository.getCurrentSongList().isEmpty()) return

        _buildingState.postValue( BuildAlbumState.MAKING )
        CoroutineScope( Dispatchers.Default ).launch {
            val list = repository.getCurrentSongList().filter { it.collectionId == id }.sortedBy { it.trackId }
            _buildingState.postValue( BuildAlbumState.FINISHED )
            _songAlbumList.postValue( list )
        }
    }
}