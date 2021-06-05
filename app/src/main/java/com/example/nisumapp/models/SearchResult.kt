package com.example.nisumapp.models

import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("resultCount") val count: Int,
    val results: List<Song>
)