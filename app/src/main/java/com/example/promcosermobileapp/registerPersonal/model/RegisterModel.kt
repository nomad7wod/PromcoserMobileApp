package com.example.promcosermobileapp.registerPersonal.model

class RegisterModel {
    data class RegisterRequest(
        val nombre: String,
        val apellido: String,
        val idRol: Int = 1, // Valor predeterminado
        val telefono: String,
        val correo: String,
        val dni: String,
        val estado: Boolean = true, // Valor predeterminado
        val fechNacimiento: String,
        val idUbigeo: Int = 1, // Valor predeterminado
        val username: String,
        val password: String
    )

    data class RegisterResponse(
        val success: Boolean,
        val message: String
    )
}

