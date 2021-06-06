package com.example.nisumapp.ui.child

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nisumapp.repos.SearchRepository


class AlbumViewModelFactory(var repository: SearchRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = AlbumViewModel( repository ) as T
}


class AlbumViewModel(var repository: SearchRepository) : ViewModel() {
    // TODO: Implement the ViewModel
}