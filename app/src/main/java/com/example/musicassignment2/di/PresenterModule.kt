package com.example.musicassignment2.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.musicassignment2.database.MusicDataBase
import com.example.musicassignment2.presenters.*
import com.example.musicassignment2.rest.MusicApi
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {
    /**
     * This method is for provide the Rock Presenter
     * when necessary
     */
    @Provides
    fun provideRockPresenter(context: Context, musicAPI: MusicApi, connectivityManager: ConnectivityManager, musicDatabase: MusicDataBase): IRockPresenter {
        return RockPresenter(context,musicAPI, connectivityManager,musicDatabase)
    }

    @Provides
    fun provideClassicPresenter(context: Context,musicAPI: MusicApi, connectivityManager: ConnectivityManager,musicDatabase: MusicDataBase): IClassicPresenter {
        return ClassicPresenter(context,musicAPI, connectivityManager,musicDatabase)
    }

    /**
     * This method is for provide the Pop Presenter
     * when necessary
     */
    @Provides
    fun providePopPresenter(context: Context,musicAPI: MusicApi, connectivityManager: ConnectivityManager,musicDatabase: MusicDataBase): IPopPresenter {
        return PopPresenter(context,musicAPI, connectivityManager,musicDatabase)
    }
}