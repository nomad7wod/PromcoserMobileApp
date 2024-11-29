package com.example.promcosermobileapp.ui.home.repository

import com.example.promcosermobileapp.ui.home.model.DetalleParteDiarioModel
import com.example.promcosermobileapp.ui.home.service.DetalleService
import com.example.promcosermobileapp.ui.personal.service.PersonalApiService
import com.example.promcosermobileapp.ui.personal.service.RetrofitInstance

class DetalleRepository {
    private val DetalleService : DetalleService = RetrofitInstance.api

    fun createDetalleDiario(detalle: DetalleParteDiarioModel) = DetalleService.createDetalleParteDiario(detalle)
}