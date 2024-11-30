package com.example.promcosermobileapp.registerPersonal.model

class RegisterModel {
    data class RegisterRequest(
        val nombre: String,
        val apellido: String,
        val telefono: String,
        val correo: String,
        val dni: String,
        val fechNacimiento: String,
        val idUbigeo: Int,
        val username: String,
        val password: String,
        val idRol: Int = 1, // Valor predeterminado
        val estado: Boolean = true // Valor predeterminado
    )

    data class RegisterResponse(
        val success: Boolean,
        val message: String
    )
}
