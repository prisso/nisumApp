package com.example.nisumapp.ui.child

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.nisumapp.models.*
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class AlbumViewModelTest {

    private lateinit var viewModel: AlbumViewModel

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val songListObserver: Observer<List<Song>> = mock()

    @Before
    fun setup() {
        viewModel = AlbumViewModel( SearchRepositoryMock() )
        viewModel.songList.observeForever(songListObserver)
    }

    @Test
    fun testMakeAlbumFromCollectionIdWhenIDIsContainedInList() {
        viewModel.makeAlbumFromCollection( testCollectionId )

        verify(songListObserver).onChanged(listOf(testSong1, testSong2))
    }

    @Test
    fun testMakeAlbumFromCollectionIdWhenIDIsNotInList() {
        viewModel.makeAlbumFromCollection( -1 )

        verify(songListObserver).onChanged(listOf())
    }

    @Test
    fun testLoadInfoFromCollection() {

    }
}