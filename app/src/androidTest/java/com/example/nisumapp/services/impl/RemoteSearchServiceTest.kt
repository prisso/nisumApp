package com.example.nisumapp.services.impl

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class RemoteSearchServiceTest {

    private val service = RemoteSearchService()
    private val knownTerm = "in utero"
    private val unknownTerm = "kdjfsdkjfsdk"

    @Test
    fun testSearchForEmptyTerm() = runBlocking {
        val list = service.searchFor("" )
        Assert.assertTrue( list.isEmpty() )
    }

    @Test
    fun testSearchForKnownTerm() = runBlocking {
        val list = service.searchFor( knownTerm )
        Assert.assertTrue( list.isNotEmpty() )
    }

    @Test
    fun testSearchForUnknownTerm() = runBlocking {
        val list = service.searchFor( unknownTerm )
        Assert.assertFalse( list.isNotEmpty() )
    }
}