package com.example.promcosermobileapp.ui.home.service

import com.example.promcosermobileapp.ui.home.repository.ParteDiarioApiRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val base_url = "https://d851-200-0-166-76.ngrok-free.app/api/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    val api: ParteDiarioService by lazy {
        retrofit.create(ParteDiarioService::class.java)
    }

}