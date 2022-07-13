package com.utp.comidaencasav1.model

import java.io.Serializable
import java.util.*

class Plato(
    var idCuenta: Int = 0,
    var idDocumento: String = "",
    var idPlato: Int = 0,
    var idUsuarioCreador: Int = 0,
    var nombre: String = "",
    var preparacion: String = "",
    var idCategoria: Int = Categoria.idCategoria.BALANCEADO,
    var estadoVisibilidad: Boolean = true,
    var estadoCategoriaSemanal: Boolean = true,
    var fechaCategoriaSemanal: Date? = null
) : Serializable {

}