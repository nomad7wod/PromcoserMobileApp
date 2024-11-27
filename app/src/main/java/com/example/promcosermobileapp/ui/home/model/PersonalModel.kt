package com.example.promcosermobileapp.ui.home.model

import java.sql.Date

data class PersonalModel(
    val idPersonal: Int,
    val nombre: String,
    val apellido: String,
    val idRol: Int,
    val telefono: String,
    val correo: String,
    val dni: String,
    val estado: Boolean,
    val fechaNacimiento: Date,
    val idUbigeo: Int,
    val username: String,
    val password: String
)
