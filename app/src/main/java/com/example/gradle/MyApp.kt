package com.example.gradle

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.qualifiers.ApplicationContext


class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}