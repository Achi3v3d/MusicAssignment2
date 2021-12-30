package com.example.musicassignment2.di

import com.example.musicassignment2.rest.MusicApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    /**
     * This method provide the Logging Interceptor
     */
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    /**
     * This method provide the OkHttpClient
     * @param loggingInterceptor will be provided by dagger
     */
    @Provides
    @Singleton
    fun providesHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    /**
     * This method provide the networkAPI using Retrofit
     */
    @Provides
    @Singleton
    fun provideNetworkAPI(okHttpClient: OkHttpClient): MusicApi {
        return Retrofit.Builder()
            .baseUrl(MusicApi.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MusicApi::class.java)
    }
}