package com.example.promcosermobileapp.ui.cliente.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val base_url = "https://0768-190-236-195-222.ngrok-free.app/api/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    val api: ClienteApiService by lazy {
        retrofit.create(ClienteApiService::class.java)
    }

}