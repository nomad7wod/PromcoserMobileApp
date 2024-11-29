package com.example.promcosermobileapp.ui.home.model

data class ClienteModel (
    val idCliente: Int,
    val tipoCliente: String,
    val telefono: String,
    val correo: String,
    val direccion: String,
    val estado: Boolean,
    val nombre: String,
    val apellido: String,
    val dni: String,
    val razonSocial: String,
    val ruc: String
)