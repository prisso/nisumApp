package com.example.nisumapp.ui.child

sealed class AlbumViewState {

    data class albumTitleUpdated(val title: String): AlbumViewState()

    data class bandNameUpdated(val name: String): AlbumViewState()

    data class artworkUpdated(val url: String): AlbumViewState()
}
