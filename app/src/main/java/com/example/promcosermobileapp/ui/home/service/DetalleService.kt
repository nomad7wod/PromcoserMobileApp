package com.example.promcosermobileapp.ui.home.service

import com.example.promcosermobileapp.ui.home.model.DetalleParteDiarioModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface DetalleService {
    @POST("DetalleParteDiarios")
    fun createDetalleParteDiario(@Body detalleParteDiario: DetalleParteDiarioModel): Call<DetalleParteDiarioModel>

}