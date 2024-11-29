package com.example.promcosermobileapp.ui.cliente.service

import com.example.promcosermobileapp.ui.cliente.model.ClienteModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ClienteApiService {
    @GET("Clientes")
    fun getClientes(): Call<List<ClienteModel>>
    @POST("Clientes")
    fun createCliente(@Body cliente: ClienteModel): Call<ClienteModel>
}