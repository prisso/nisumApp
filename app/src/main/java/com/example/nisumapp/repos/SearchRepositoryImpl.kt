package com.example.nisumapp.repos

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.nisumapp.models.Song
import com.example.nisumapp.services.SearchService
import com.example.nisumapp.services.StorableSearchService


class SearchRepositoryImpl(private val context: Context,
                           private val localService: StorableSearchService,
                           private val remoteService: SearchService): SearchRepository {

    private var currentService: SearchService? = null
    private val songsList = mutableListOf<Song>()
    private var currentSearchTerm: String = ""


    override suspend fun searchFor(term: String): List<Song> {
        songsList.clear()
        currentService = if (isOnline()) remoteService else localService

        currentSearchTerm = term
        val songs = currentService?.searchFor( term )
        songs?.let { songsList.addAll(it) }

        localService.saveResultFor(term, songsList)
        return songsList
    }

    private fun isOnline(): Boolean {
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?)?.let {  connectivityManager ->
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.let { capabilities ->
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    return true
                }
            }
        }
        return false
    }

    override fun getCurrentSongList(): List<Song> = songsList.toList()
}