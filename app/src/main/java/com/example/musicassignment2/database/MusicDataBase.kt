package com.example.musicassignment2.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.musicassignment2.model.classic.ClassicMusic
import com.example.musicassignment2.model.pop.PopMusic
import com.example.musicassignment2.model.rock.RockMusic

@Database(entities = [RockMusic::class,ClassicMusic::class,PopMusic::class], version = 1)
abstract class MusicDataBase: RoomDatabase() {
    abstract  fun getMusic(): Dao
}