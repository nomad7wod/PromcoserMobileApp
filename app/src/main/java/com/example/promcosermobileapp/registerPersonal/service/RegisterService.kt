package com.example.promcosermobileapp.registerPersonal.service

import com.example.promcosermobileapp.registerPersonal.model.RegisterModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RegisterService {
    @Headers("Content-Type: application/json")
    @POST("SignUp")
    fun register(@Body request: RegisterModel.RegisterRequest): Call<RegisterModel.RegisterResponse>
}


