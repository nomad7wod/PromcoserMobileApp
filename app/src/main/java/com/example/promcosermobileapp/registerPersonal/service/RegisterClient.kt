package com.example.promcosermobileapp.registerPersonal.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RegisterClient {
    private val base_url = "https://22c4-2001-1388-ae1-198e-8c7a-96a7-be33-e333.ngrok-free.app/api/Personal/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: RegisterService by lazy {
        retrofit.create(RegisterService::class.java)
    }
}