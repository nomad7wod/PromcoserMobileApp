package com.example.promcosermobileapp.login.model

data class LoginRequest(
    val correo: String,
    val password: String
)

data class LoginResponse(
    val success: Boolean,
    val message: String
)
