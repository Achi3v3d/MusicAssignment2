package com.example.musicassignment2.model.pop


import com.google.gson.annotations.SerializedName

data class PopModel(
    @SerializedName("resultCount")
    val resultCount: Int,
    @SerializedName("results")
    val popMusics: List<PopMusic>
)