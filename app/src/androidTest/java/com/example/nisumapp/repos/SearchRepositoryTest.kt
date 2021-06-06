package com.example.nisumapp.repos

import androidx.test.platform.app.InstrumentationRegistry
import com.example.nisumapp.models.*
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.equalTo
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class SearchRepositoryTest {

    private lateinit var repository: SearchRepositoryImpl

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val localService = LocalSearchServiceMock()
        val remoteService = RemoteSearchServiceMock()

        repository = SearchRepositoryImpl(context, localService, remoteService)
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

    // TODO: How do I make to test when there isn't a internet connection?
}