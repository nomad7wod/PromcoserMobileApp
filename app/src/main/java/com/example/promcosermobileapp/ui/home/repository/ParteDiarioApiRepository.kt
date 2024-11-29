package com.example.promcosermobileapp.ui.home.repository

import com.example.promcosermobileapp.ui.home.model.ParteDiarioModel
import com.example.promcosermobileapp.ui.home.service.ParteDiarioService
import com.example.promcosermobileapp.ui.home.service.RetrofitInstance

class ParteDiarioApiRepository {
    private val ParteDiarioService: ParteDiarioService =  RetrofitInstance.api

    fun createParteDiario(parteDiario: ParteDiarioModel) = ParteDiarioService.createParteDiario(parteDiario)
}