package com.example.composetaskappandroid.Retrofit

data class AdviceResponse(
    val slip: Slip
)

data class Slip(
    val id: Int,
    val advice: String
)
