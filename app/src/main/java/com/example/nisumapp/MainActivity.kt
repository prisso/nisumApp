package com.example.nisumapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nisumapp.repos.SearchRepository
import com.example.nisumapp.services.impl.LocalSearchService
import com.example.nisumapp.services.impl.RemoteSearchService
import com.example.nisumapp.services.impl.SharedPrefManager
import com.example.nisumapp.ui.main.SearchFragment

class MainActivity : AppCompatActivity() {

    private val searchRepository: SearchRepository by lazy {
        val local = LocalSearchService( SharedPrefManager( this.applicationContext ) )
        SearchRepository(this.applicationContext, local, RemoteSearchService())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }


    fun getRepository(): SearchRepository = searchRepository
}