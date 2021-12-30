package com.example.musicassignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.musicassignment2.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.example.musicassignment2.adaptor.FragmentAdaptor

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    /**
     * In on onCreate Method of main activity we bind the adapter for the 3 fragments
     * and we define de position of them
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.musicContainer.adapter = FragmentAdaptor(supportFragmentManager,lifecycle)

        TabLayoutMediator(binding.musicTab,binding.musicContainer){tab, position ->
            when(position){
                0-> {
                    tab.text="Rock"
                    tab.icon=getDrawable(R.drawable.ic_rock_music)
                }
                1 -> {
                    tab.text="Classic"
                    tab.icon=getDrawable(R.drawable.ic_classic_music)
                }
                else -> {
                    tab.text="Pop"
                    tab.icon=getDrawable(R.drawable.ic_pop_music)
                }
            }
        }.attach()

    }
}