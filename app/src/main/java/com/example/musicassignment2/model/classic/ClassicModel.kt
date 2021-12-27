package com.example.musicassignment2.model.classic


import com.google.gson.annotations.SerializedName

data class ClassicModel(
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("results")
    val classicMusics: List<ClassicMusic>
)