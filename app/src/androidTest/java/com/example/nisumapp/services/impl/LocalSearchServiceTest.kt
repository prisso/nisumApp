package com.example.nisumapp.services.impl

import androidx.test.platform.app.InstrumentationRegistry
import com.example.nisumapp.models.testSongs
import com.example.nisumapp.models.testTerm
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LocalSearchServiceTest {

    private lateinit var service: LocalSearchService
    private val knownTerm = "in utero"
    private val unknownTerm = "kdjfsdkjfsdk"

    @Before
    fun setup() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val storageService = SharedPrefManager( context )
        service = LocalSearchService( storageService )
        service.saveResultFor(testTerm, testSongs)
    }

    @Test
    fun testSearchForKnownTerm() = runBlocking {
        val list = service.searchFor( knownTerm )
        Assert.assertTrue( list.isNotEmpty() )
    }

    @Test
    fun testSearchForUnknownTerm() = runBlocking {
        var list = service.searchFor( unknownTerm )
        Assert.assertFalse( list.isNotEmpty() )
    }
}