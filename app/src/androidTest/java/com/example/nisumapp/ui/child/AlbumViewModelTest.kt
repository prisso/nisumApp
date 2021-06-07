package com.example.nisumapp.ui.child

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
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
    private val viewStateObserver: Observer<AlbumViewState> = mock()
    private val buildAlbumStateObserver: Observer<BuildAlbumState> = mock()

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        viewModel = AlbumViewModel( SearchRepositoryMock() )
        viewModel.viewState.observeForever(viewStateObserver)
        viewModel.buildAlbumState.observeForever(buildAlbumStateObserver)
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
        viewModel.loadInfo()

        verify(viewStateObserver).onChanged(AlbumViewState.artworkUpdated(testSong1.artworkUrl))
        verify(viewStateObserver).onChanged(AlbumViewState.bandNameUpdated(testSong1.artistName))
        verify(viewStateObserver).onChanged(AlbumViewState.albumTitleUpdated(testSong1.collectionName))
    }

    @Test
    fun testWhenStateAlbumWhenItsBuilding() {
        viewModel.makeAlbumFromCollection( testCollectionId )

        verify(buildAlbumStateObserver).onChanged(BuildAlbumState.MAKING)
        // How should I do to test finished event?
    }
}