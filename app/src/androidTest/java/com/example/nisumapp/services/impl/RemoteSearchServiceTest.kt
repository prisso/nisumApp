package com.example.nisumapp.services.impl

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class RemoteSearchServiceTest {

    private val service = RemoteSearchService()
    private val knownTerm = "in utero"
    private val unknownTerm = "kdjfsdkjfsdk"

    @Test
    fun testSearchForKnownTerm() = runBlocking {
        var list = service.searchFor( knownTerm )
        Assert.assertTrue( list.isNotEmpty() )
    }

    @Test
    fun testSearchForUnknownTerm() = runBlocking {
        var list = service.searchFor( unknownTerm )
        Assert.assertFalse( list.isNotEmpty() )
    }
}