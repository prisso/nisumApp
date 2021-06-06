package com.example.nisumapp.repos

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.nisumapp.models.Song
import com.example.nisumapp.services.SearchService
import com.example.nisumapp.services.StorableSearchService
import kotlinx.coroutines.*


class SearchRepository(private val context: Context,
                       private val localService: StorableSearchService,
                       private val remoteService: SearchService) {

    private var currentService: SearchService? = null
    private val songsList = mutableListOf<Song>()
    private var currentSearchTerm: String = ""


    suspend fun searchFor(term: String): List<Song> {
        songsList.clear()
        currentService = if (isOnline()) remoteService else localService

        currentSearchTerm = term
        val songs = currentService?.searchFor( term )
        songs?.let { songsList.addAll(it) }

        localService.saveResultFor(term, songsList)
        return songsList
    }

    // I think that this method shouldn't be here and must be moved to AlbumViewModel. Change for next commit
    suspend fun makeAlbumFromCollection(id: Int): List<Song> {
        val job = CoroutineScope( Dispatchers.Default ).async {
            songsList.filter { it.collectionId == id }.sortedBy { it.trackId }
        }

        if (songsList.isEmpty()) {
            job.cancelAndJoin()
            return emptyList()
        }

        val album: List<Song> = job.await()
        return  album
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

    fun getCurrentSearchTerm(): String = this.currentSearchTerm

    fun getCurrentSongList(): List<Song> = songsList.toList()
}