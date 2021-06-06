package com.example.nisumapp.repos

import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import com.example.nisumapp.models.*
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.equalTo
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class SearchRepositoryTest {

    private lateinit var repository: SearchRepository

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val localService = LocalSearchServiceMock()
        val remoteService = RemoteSearchServiceMock()

        repository = SearchRepository(context, localService, remoteService)
    }

    @Test
    fun testWhenSearchTermIsKnown() = runBlocking {
        val list = repository.searchFor( testTerm )
        Assert.assertThat(list, equalTo(testSongs))
    }

    @Test
    fun testWhenSearchTermIsUnknown() = runBlocking {
        val list = repository.searchFor( unknownTestTerm )
        Assert.assertThat(list.size, equalTo(0))
    }

    @Test
    fun testMakeAlbumFromCollectionIdWhenIDIsContainedInList() = runBlocking {
        repository.searchFor( testTerm )
        val album = repository.makeAlbumFromCollection( testCollectionId )

        Assert.assertThat(album.size, equalTo(2))
        Assert.assertThat(album[0], equalTo(testSong1))
        Assert.assertThat(album[1], equalTo(testSong2))
    }

    @Test
    fun testMakeAlbumFromCollectionIdWhenIDIsNotInList() = runBlocking {
        repository.searchFor( testTerm )
        val album = repository.makeAlbumFromCollection( -1 )

        Assert.assertThat(album.size, equalTo(0))
    }

    // TODO: How do I make to test when there isn't a internet connection?
}