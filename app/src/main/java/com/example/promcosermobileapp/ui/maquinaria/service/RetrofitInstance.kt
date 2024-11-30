package com.example.promcosermobileapp.ui.maquinaria.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

<<<<<<< HEAD
    private val base_url = " https://a4df-2001-1388-ae1-198e-8c7a-96a7-be33-e333.ngrok-free.app/api/"
=======
    private val base_url = "https://9193-201-240-147-236.ngrok-free.app/api/"
>>>>>>> cef8fe8e6ceb691e25e6cd51246a1095ea8f2bb0
   

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