package com.code93.e_movie

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class EMovie : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}