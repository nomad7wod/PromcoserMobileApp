package com.example.promcosermobileapp.ui.personal.model

import com.google.gson.annotations.SerializedName
import java.sql.Date

data class PersonalModel(
    @SerializedName("idPersonal")val id_personal: Int? = null,
    val nombre: String,
    val apellido: String,
    val id_rol: Int,
    val telefono: String,
    val correo: String,
    val dni: String,
    val estado: Boolean = false,
    val fech_nacimiento: Date,
    val id_ubigeo: Int,
    val username: String,
    val password: String
)
