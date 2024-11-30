package com.example.promcosermobileapp.registerPersonal.repository

import com.example.promcosermobileapp.registerPersonal.model.RegisterModel
import com.example.promcosermobileapp.registerPersonal.service.RegisterClient
import com.example.promcosermobileapp.registerPersonal.service.RegisterService
import retrofit2.Call

class RegisterRepository {
    private val RegisterApiService: RegisterService = RegisterClient.api

    fun register(request: RegisterModel.RegisterRequest): Call<RegisterModel.RegisterResponse> {
        return RegisterApiService.register(request)
    }
}
