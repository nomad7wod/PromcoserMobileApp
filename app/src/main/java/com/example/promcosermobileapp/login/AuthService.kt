package com.example.promcosermobileapp.login

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val success: Boolean, val message: String)

interface AuthService {
    @POST("SignIn/")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}
