package com.example.nisumapp.ui.child

import com.example.nisumapp.models.Song
import com.example.nisumapp.models.testSongs
import com.example.nisumapp.repos.SearchRepository


class SearchRepositoryMock: SearchRepository {

    override suspend fun searchFor(term: String): List<Song> {
        return testSongs
    }

    override fun getCurrentSongList(): List<Song> {
        return testSongs
    }
}