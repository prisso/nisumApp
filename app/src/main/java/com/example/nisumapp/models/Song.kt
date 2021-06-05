package com.example.nisumapp.models

import com.google.gson.annotations.SerializedName


data class Song(val artistId: Int,
                val collectionId:Int,
                val trackId: Int,
                val artistName: String,
                val collectionName: String,
                val trackName: String,
                val previewUrl: String,
                @SerializedName("artworkUrl100") val artworkUrl: String,
                val trackTimeMillis: Long,
                val country: String,
                val shortDescription: String
)