package com.example.nisumapp.repos

import com.example.nisumapp.models.Song


interface SearchRepository {

    suspend fun searchFor(term: String): List<Song>

    fun getCurrentSongList(): List<Song>

}