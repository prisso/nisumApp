package com.example.nisumapp.services.impl

import androidx.test.platform.app.InstrumentationRegistry
import com.example.nisumapp.models.testSongs
import com.example.nisumapp.models.testTerm
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.equalTo
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class SharedPrefManagerTest {

    private lateinit var manager: SharedPrefManager

    @Before
    fun setup() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        manager = SharedPrefManager(context)
        manager.save(testTerm, testSongs)
    }

    @Test
    fun testSaveLoadProcess() = runBlocking {
        val result = manager.load(testTerm)

        Assert.assertThat(result, equalTo(testSongs))
    }
}