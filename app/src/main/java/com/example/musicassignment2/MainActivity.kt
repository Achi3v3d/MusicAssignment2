package com.example.musicassignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.musicassignment2.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    /**
     * In on onCreate Method of main activity we bind the adapter for the 3 fragments
     * and we define de position of them
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}