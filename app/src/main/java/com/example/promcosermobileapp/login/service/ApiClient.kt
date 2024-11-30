package com.example.promcosermobileapp.login.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
<<<<<<< HEAD
    private val base_url = "https://655d-2800-200-e731-a8c-289f-f79f-ffbe-ffe6.ngrok-free.app/api/"
=======
    private val base_url = "https://9193-201-240-147-236.ngrok-free.app/api/Personal/"
>>>>>>> c86766ac155958cb413fb5ed4eff704290b3e1e1

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
