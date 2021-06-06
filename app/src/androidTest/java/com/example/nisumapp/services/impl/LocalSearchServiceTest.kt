package com.example.nisumapp.services.impl

import androidx.test.platform.app.InstrumentationRegistry
import com.example.nisumapp.models.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LocalSearchServiceTest {

    private lateinit var service: LocalSearchService

    @Before
    fun setup() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val storageService = SharedPrefManager( context )
        service = LocalSearchService( storageService )
        service.saveResultFor(testTerm, testSongs)
    }

    @Test
    fun testSearchForKnownTerm() = runBlocking {
        val list = service.searchFor( testTerm )
        Assert.assertTrue( list.isNotEmpty() )
    }

    @Test
    fun testSearchForUnknownTerm() = runBlocking {
        val list = service.searchFor( unknownTestTerm )
        Assert.assertFalse( list.isNotEmpty() )
    }
}