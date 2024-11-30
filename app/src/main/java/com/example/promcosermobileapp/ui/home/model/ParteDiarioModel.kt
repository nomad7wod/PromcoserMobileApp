package com.example.promcosermobileapp.ui.home.model

data class ParteDiarioModel (
    //val idParteDiario: Int,
    val fecha: String,
    val firmas: Boolean,
    val horometroInicio: Double,
    val horometroFinal: Double,
    val idCliente: Int,
    val idPersonal: Int,
    val idMaquinaria: Int,
    val lugarTrabajo: String?,
    val petroleo: Double,
    val aceite: Double,
    val proximoMantenimiento: String
)