package com.example.nisumapp.ui.child

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class AlbumViewModelTest {

    private lateinit var viewModel: AlbumViewModel

    @Before
    fun setup() {
        viewModel = AlbumViewModel( SearchRepositoryMock() )
    }

    @Test
    fun testMakeAlbumFromCollectionIdWhenIDIsContainedInList() = runBlocking {
    }

    @Test
    fun testMakeAlbumFromCollectionIdWhenIDIsNotInList() = runBlocking {
    }

    @Test
    fun testLoadInfoFromCollection() = runBlocking { 
    }
}
