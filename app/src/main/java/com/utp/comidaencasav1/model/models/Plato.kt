package com.utp.comidaencasav1.model.models

import java.io.Serializable
import java.util.*

class Plato(): Serializable {
    var idPlato: Int = 0
    var idCuenta: Int = 0
    var idUsuarioCreador: Int = 0
    lateinit var nombre: String
    lateinit var preparacion: String
    var idCategoria: Int = 0
    var estadoVisibilidad: Boolean = false
    var estadoCategoriaSemanal: Boolean = false
    lateinit var fechaCategoriaSemanal: Date
}