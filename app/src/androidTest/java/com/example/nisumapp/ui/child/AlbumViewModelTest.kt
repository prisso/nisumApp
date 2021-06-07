package com.example.nisumapp.ui.child

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nisumapp.models.*
import com.nhaarman.mockito_kotlin.*
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.equalTo
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class AlbumViewModelTest {

    private lateinit var viewModel: AlbumViewModel

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        viewModel = AlbumViewModel( SearchRepositoryMock() )
    }

    @Test
    fun testMakeAlbumFromCollectionIdWhenIDIsContainedInList() = runBlocking {
        viewModel.makeAlbumFromCollection( testCollectionId )
        viewModel.songList.observeForever { songList ->
            Assert.assertThat(songList, equalTo(listOf(testSong1, testSong2)))
        }
    }

    @Test
    fun testMakeAlbumFromCollectionIdWhenIDIsNotInList() {
        viewModel.makeAlbumFromCollection( -1 )
        viewModel.songList.observeForever { shouldBeEmpty ->
            Assert.assertThat(shouldBeEmpty, equalTo(listOf()))
        }
    }

    @Test
    fun testLoadInfoFromCollection() {

    }
}