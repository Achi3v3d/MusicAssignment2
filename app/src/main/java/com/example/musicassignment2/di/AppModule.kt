package com.example.musicassignment2.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.example.musicassignment2.database.MusicDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
@Module
class AppModule(    private val application: Application
) {

    /**
     * This Method provide the application context
     */
    @Provides
    @Singleton
    fun provideContext(): Context = application.applicationContext


    /**
     * This Method provides the connection manager
     */
    @Provides
    @Singleton
    fun provideConnectivityManager(context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    /**
     * This Method provides the local database
     */
    @Provides
    @Singleton
    fun provideMusicDatabase(context: Context): MusicDataBase {
        return Room.databaseBuilder(
            context,
            MusicDataBase::class.java,
            "music-db"
        ).build()
    }
}