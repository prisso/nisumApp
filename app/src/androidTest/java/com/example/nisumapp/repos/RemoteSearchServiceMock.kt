package com.example.nisumapp.repos

import com.example.nisumapp.models.Song
import com.example.nisumapp.models.testSongs
import com.example.nisumapp.models.testTerm
import com.example.nisumapp.services.SearchService


class RemoteSearchServiceMock: SearchService {
    override suspend fun searchFor(term: String): List<Song> = when(term) {
        testTerm -> testSongs
        else -> emptyList()
    }
}