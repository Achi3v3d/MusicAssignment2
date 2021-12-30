package com.example.musicassignment2.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.musicassignment2.model.classic.ClassicMusic
import com.example.musicassignment2.model.rock.RockMusic
import io.reactivex.Completable
import io.reactivex.Single
@Dao
interface Dao {
    @Query("SELECT * FROM RockMusic")
    fun getRockAll(): Single<List<RockMusic>>

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun insertRockSong(songs: List<RockMusic>): Completable

    @Query("SELECT * FROM ClassicMusic")
    fun getClassicAll(): Single<List<ClassicMusic>>

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun insertClassicSong(music: List<ClassicMusic>): Completable

    @Query("SELECT * FROM PopMusic")
    fun getPopAll(): Single<List<com.example.musicassignment2.model.pop.PopMusic>>

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    fun insertPopSong(songs: List<com.example.musicassignment2.model.pop.PopMusic>): Completable
}