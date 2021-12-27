package com.example.musicassignment2.di

import dagger.Component
import javax.inject.Singleton

@Component(
    modules=[NetworkModule::class,PresenterModule::class,AppModule::class]
)
@Singleton
interface AppComponent {
    fun inject(rockFragment: RockFragment)
    fun inject(classicFragment: ClassicFragment)
    fun inject(popFragment: PopFragment)
}