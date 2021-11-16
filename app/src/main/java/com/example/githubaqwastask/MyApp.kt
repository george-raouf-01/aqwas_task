package com.example.githubaqwastask

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {
    companion object {

        @SuppressLint("StaticFieldLeak")
         var context: Context? = null
    }


    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }


    override fun onTerminate() {
        super.onTerminate()
        context = null
    }
}