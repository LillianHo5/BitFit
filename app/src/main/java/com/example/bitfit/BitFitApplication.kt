package com.example.bitfit

import android.app.Application

class BitFitApplication : Application() {
    // A lazy initialization here just means we don't create this variable until it needs to be used
    val db by lazy { AppDatabase.getInstance(this) }
}