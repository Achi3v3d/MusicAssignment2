package com.example.musicassignment2

import android.app.Application
import com.example.musicassignment2.di.AppModule
import com.example.musicassignment2.di.DaggerMusicComponent
import com.example.musicassignment2.di.MusicComponent


class MusicApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        musicComponent= DaggerMusicComponent
            .builder()
           .appModule(AppModule(this))
           .build()

    }

    companion object{
        lateinit var musicComponent: MusicComponent
    }
}