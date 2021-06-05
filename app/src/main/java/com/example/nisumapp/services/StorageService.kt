package com.example.nisumapp.services

import com.example.nisumapp.models.Song


interface StorageService {

    suspend fun save(key: String, value: List<Song>)

    suspend fun load(key: String): List<Song>

}