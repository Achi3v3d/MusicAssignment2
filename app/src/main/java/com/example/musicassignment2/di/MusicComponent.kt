package com.example.musicassignment2.di

import com.example.musicassignment2.Views.ClassicFragment
import com.example.musicassignment2.Views.PopFragment
import com.example.musicassignment2.Views.RockFragment
import dagger.Component
import javax.inject.Singleton

@Component(
    modules=[NetworkModule::class,PresenterModule::class,AppModule::class]
)
@Singleton
interface MusicComponent {
    fun inject(rockFragment: RockFragment)
    fun inject(classicFragment: ClassicFragment)
    fun inject(popFragment: PopFragment)
}