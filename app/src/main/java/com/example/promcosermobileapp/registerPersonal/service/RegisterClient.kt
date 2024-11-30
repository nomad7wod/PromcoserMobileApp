package com.example.promcosermobileapp.registerPersonal.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RegisterClient {
    private const val BASE_URL = ""

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: RegisterService by lazy {
        retrofit.create(RegisterService::class.java)
    }
}
