package com.example.nisumapp.services

import com.example.nisumapp.models.Song


interface SearchService {

    suspend fun searchFor(term: String): List<Song>

}