package com.utp.comidaencasav1.model

import java.io.Serializable
import java.util.*

class Plato(
    var idDocumento: String = "",
    var idPlato: Int = 0,
    var idUsuarioCreador: Int = 0,
    var nombre: String = "",
    var preparacion: String = "",
    var idCategoria: Int = 0,
    var estadoVisibilidad: Boolean = false,
    var estadoCategoriaSemanal: Boolean = false,
    var fechaCategoriaSemanal: Date? = null
) : Serializable {

}