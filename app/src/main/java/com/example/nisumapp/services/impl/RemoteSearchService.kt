package com.example.nisumapp.services.impl

import com.example.nisumapp.models.Song
import com.example.nisumapp.services.SearchService

class RemoteSearchService : SearchService {

    override suspend fun searchFor(term: String): List<Song> {
        if (term.isEmpty()) return emptyList()

        val searchResult = SearchApi.create().searchTerm(term).await()
        return searchResult.results
    }

}