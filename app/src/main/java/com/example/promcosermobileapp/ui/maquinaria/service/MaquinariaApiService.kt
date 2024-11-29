package com.example.promcosermobileapp.ui.maquinaria.service

import com.example.promcosermobileapp.ui.home.model.Maquinaria
import com.example.promcosermobileapp.ui.personal.model.PersonalModel
import com.example.promcosermobileapp.ui.personal.model.Ubigeomodel
import com.example.promcosermobileapp.ui.personal.model.rolmodel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MaquinariaApiService {
    @GET("Maquinaria")
    fun getMaquinaria(): Call<List<Maquinaria>>



}