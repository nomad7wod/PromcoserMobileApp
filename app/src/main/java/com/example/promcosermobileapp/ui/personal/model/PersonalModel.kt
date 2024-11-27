package com.example.promcosermobileapp.ui.personal.model

data class PersonalModel(
    //val id_personal: Int,
    val nombre: String,
    val apellido: String,
    val idRol: Int,
    val telefono: String,
    val correo: String,
    val dni: String,
    val Estado: Boolean = false,
    val fechNacimiento: String,
    val idUbigeo: Int,
    val username: String,
    val password: String
)
