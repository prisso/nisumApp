package com.example.nisumapp.services.impl

import com.example.nisumapp.models.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class RemoteSearchServiceTest {

    private val service = RemoteSearchService()

    @Test
    fun testSearchForEmptyTerm() = runBlocking {
        val list = service.searchFor("" )
        Assert.assertTrue( list.isEmpty() )
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