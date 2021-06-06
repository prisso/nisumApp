package com.example.nisumapp.services

import com.example.nisumapp.models.Song


interface StorableSearchService: SearchService {

    suspend fun saveResultFor(term: String, result: List<Song>)

}