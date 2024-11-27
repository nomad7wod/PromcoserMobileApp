package com.example.promcosermobileapp.ui.home.service

import com.example.promcosermobileapp.ui.home.model.ClienteModel
import com.example.promcosermobileapp.ui.home.model.MaquinariaModel
import com.example.promcosermobileapp.ui.home.model.ParteDiarioModel
import com.example.promcosermobileapp.ui.home.model.PersonalModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ParteDiarioService {
    @GET("ParteDiario")
    fun getParteDiario(): Call<List<ParteDiarioModel>>
    @POST("ParteDiario")
    fun createParteDiario(@Body parteDiario: ParteDiarioModel): Call<ParteDiarioModel>
    @GET("Clientes")
    fun getCliente(): Call<List<ClienteModel>>
    @GET("Maquinarias")
    fun getMaquinaria(): Call<List<MaquinariaModel>>
    @GET("Personal")
    fun getPersonal(): Call<List<PersonalModel>>
}