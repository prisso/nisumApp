package com.example.nisumapp.services.impl

import com.example.nisumapp.models.Song
import com.example.nisumapp.services.StorableSearchService
import com.example.nisumapp.services.StorageService


class LocalSearchService(private val storageService: StorageService): StorableSearchService {

    override suspend fun searchFor(term: String): List<Song> {
        return storageService.load(term)
    }

    override suspend fun saveResultFor(term: String, result: List<Song>) {
        storageService.save(term, result)
    }

}