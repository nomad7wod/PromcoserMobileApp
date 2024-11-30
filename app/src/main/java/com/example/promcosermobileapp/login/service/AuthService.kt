package com.example.promcosermobileapp.login.service

import com.example.promcosermobileapp.login.model.LoginRequest
import com.example.promcosermobileapp.login.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthService {
    @Headers("Content-Type: application/json")
    @POST("SignIn")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}
