package com.example.promcosermobileapp.login.repository

import com.example.promcosermobileapp.login.model.LoginRequest
import com.example.promcosermobileapp.login.model.LoginResponse
import com.example.promcosermobileapp.login.service.ApiClient
import com.example.promcosermobileapp.login.service.AuthService
import retrofit2.Call

class LoginRepository {

    private val loginApiService: AuthService = ApiClient.api

    fun login(request: LoginRequest): Call<LoginResponse> {
        return loginApiService.login(request)
    }
}
