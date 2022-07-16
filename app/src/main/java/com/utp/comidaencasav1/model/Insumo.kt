package com.utp.comidaencasav1.model

import java.io.Serializable
import java.util.*

class Insumo(
    var idDocumento: String = "",
    var idInsumo: Int = 0,
    var idCuenta: Int = 0,
    var nombre: String = "",
    var cantidad: Float = 0f,
    var unidad: String = "",
) : Serializable {

}