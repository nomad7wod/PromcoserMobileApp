package com.example.promcosermobileapp.ui.maquinaria.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {


    private val base_url = ""

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