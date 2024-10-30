package com.example.composetaskappandroid.Retrofit

import retrofit2.http.GET

interface AdviceApi {
    @GET("advice")
    suspend fun getAdvice(): AdviceResponse
}
