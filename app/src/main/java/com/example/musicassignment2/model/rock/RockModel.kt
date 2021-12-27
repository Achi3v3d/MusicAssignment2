package com.example.musicassignment2.model.rock


import com.google.gson.annotations.SerializedName

data class RockModel(
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("results")
    val rockMusics: List<RockMusic>
)