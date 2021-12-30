package com.example.musicassignment2.rest

import com.example.musicassignment2.model.classic.ClassicModel
import com.example.musicassignment2.model.pop.PopModel
import com.example.musicassignment2.model.rock.RockModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicApi {

    @GET(SEARCH)
    fun getRock(
        @Query("term") type:String= ROCK,
        @Query("amp;media") media:String = MEDIA,
        @Query("amp;entity") entity:String = ENTITY,
        @Query("limit") limit:String = LIMIT

    ): Single<RockModel>

    @GET(SEARCH)
    fun getClassic(
        @Query("term") type:String= CLASSIC,
        @Query("amp;media") media:String = MEDIA,
        @Query("amp;entity") entity:String = ENTITY,
        @Query("limit") limit:String = LIMIT

    ): Single<ClassicModel>

    @GET(SEARCH)
    fun getPop(
        @Query("term") type:String= POP,
        @Query("amp;media") media:String = MEDIA,
        @Query("amp;entity") entity:String = ENTITY,
        @Query("limit") limit:String = LIMIT

    ): Single<PopModel>

    companion object {
        const val BASE_URL = "https://itunes.apple.com/"
        const val SEARCH="search"
        const val ROCK="rock"
        const val CLASSIC="classick"
        const val POP="pop"
        const val MEDIA="music"
        const val ENTITY="song"
        const val LIMIT="200"
    }
}