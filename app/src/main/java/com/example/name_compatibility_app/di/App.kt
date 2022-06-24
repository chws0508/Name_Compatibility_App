package com.example.name_compatibility_app.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltAndroidApp
class App : Application() {
    companion object{
        private lateinit var application: App
        fun getInstance(): App = application
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }
}
