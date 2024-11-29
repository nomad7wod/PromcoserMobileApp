package com.example.promcosermobileapp.ui.cliente.repository

import com.example.promcosermobileapp.ui.cliente.model.ClienteModel
import com.example.promcosermobileapp.ui.cliente.service.ClienteApiService
import com.example.promcosermobileapp.ui.cliente.service.RetrofitInstance

class ClienteAPiRepository {

    private val ClienteApiService: ClienteApiService = RetrofitInstance.api

    fun createCliente(cliente : ClienteModel) = ClienteApiService.createCliente(cliente)
}