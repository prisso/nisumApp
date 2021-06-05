package com.example.nisumapp.services.impl

import android.content.Context
import android.content.SharedPreferences
import com.example.nisumapp.models.Song
import com.example.nisumapp.services.StorageService
import com.google.gson.Gson


class SharedPrefManager(private val context: Context) : StorageService {

    private val gson = Gson()

    private fun get(): SharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)

    override suspend fun save(key: String, value: List<Song>) {
        val setOfSongs = mutableSetOf<String>()
        for (song in value) {
            setOfSongs.add( gson.toJson( song ) )
        }
        get().edit().putStringSet(key, setOfSongs).apply()
    }

    override suspend fun load(key: String): List<Song> {
        get().getStringSet(key, null)?.let { records ->
            val songs = mutableListOf<Song>()
            for (record in records) {
                songs.add( gson.fromJson(record, Song::class.java) )
            }
            return songs
        } ?: return emptyList()
    }

    companion object {
        private const val SP_NAME = "NisumChallenge"
    }
}
