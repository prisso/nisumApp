package com.example.nisumapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nisumapp.repos.SearchRepository


class SearchViewModelFactory(var repository: SearchRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = SearchViewModel( repository ) as T
}


class SearchViewModel(var repository: SearchRepository) : ViewModel() {

    // TODO: Implement the ViewModel
}