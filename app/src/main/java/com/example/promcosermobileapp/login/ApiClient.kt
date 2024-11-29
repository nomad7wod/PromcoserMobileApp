package com.example.promcosermobileapp.login

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val base_url = "http://04e0-2001-1388-ae1-a8c4-7466-7948-ce8e-d9e8.ngrok-free.app/api/personal/"

    public val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api: AuthService by lazy {
        retrofit.create(AuthService::class.java)
    }
}

