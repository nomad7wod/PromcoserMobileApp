package com.example.promcosermobileapp.ui.personal.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val base_url = "https://1971-190-236-195-222.ngrok-free.app/api/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    val api: PersonalApiService by lazy {
        retrofit.create(PersonalApiService::class.java)
    }

}