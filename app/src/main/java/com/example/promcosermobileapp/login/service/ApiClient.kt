package com.example.promcosermobileapp.login.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val base_url = "https://d851-200-0-166-76.ngrok-free.app/api/Personal/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: AuthService by lazy {
        retrofit.create(AuthService::class.java)
    }
}
