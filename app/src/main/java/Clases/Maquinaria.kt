package Clases

data class Maquinaria(
    val modelo: String,
    val marca: String,
    val placa: String,
    val horometro: Int,
    val anoCompra: Int,
    val estado: String,
    val fechaUso: String?,
    val horasUso: Int?,
    val lugarTrabajo: String?,
    val petroleoUsado: Double?,
    val aceiteHidraulico: Double?
)
