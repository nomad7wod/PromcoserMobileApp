package com.example.promcosermobileapp.ui.maquinaria.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val base_url = " https://a4df-2001-1388-ae1-198e-8c7a-96a7-be33-e333.ngrok-free.app/api/"
   

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    val api: MaquinariaApiService by lazy {
        retrofit.create(MaquinariaApiService::class.java)
    }

}