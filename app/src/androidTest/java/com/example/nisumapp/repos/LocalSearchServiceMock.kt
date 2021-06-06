package com.example.nisumapp.repos

import com.example.nisumapp.models.Song
import com.example.nisumapp.models.testSongs
import com.example.nisumapp.models.testTerm
import com.example.nisumapp.services.StorableSearchService


class LocalSearchServiceMock: StorableSearchService {

    override suspend fun saveResultFor(term: String, result: List<Song>) {
        // Do nothing
    }

    override suspend fun searchFor(term: String): List<Song>  = when(term) {
        testTerm -> testSongs
        else -> emptyList()
    }
}