package com.example.promcosermobileapp.ui.maquinaria.repository

import com.example.promcosermobileapp.ui.cliente.model.ClienteModel
import com.example.promcosermobileapp.ui.home.model.Maquinaria
import com.example.promcosermobileapp.ui.home.model.MaquinariaModel
import com.example.promcosermobileapp.ui.maquinaria.service.MaquinariaApiService
import com.example.promcosermobileapp.ui.maquinaria.service.RetrofitInstance

class MaquinariaAPiRepository {

    private val MaquinariaApiService: MaquinariaApiService = RetrofitInstance.api

    fun getMaquinaria() = MaquinariaApiService.getMaquinaria()

    fun postMaquinaria(maquinaria : Maquinaria) = MaquinariaApiService.createMaquinaria(maquinaria)

}