package com.example.nisumapp.services.impl

import com.example.nisumapp.models.SearchResult
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("/search?media=music")
    suspend fun searchTerm(
        @Query("term") term: String
    ): Deferred<SearchResult>

    companion object {
        private const val BASE_URL = "http://itunes.apple.com/"

        fun create(): SearchApi {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SearchApi::class.java)
        }
    }
}