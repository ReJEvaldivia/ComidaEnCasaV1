package com.utp.comidaencasav1.model

import java.io.Serializable
import java.util.*

class PlatoDia(
    var idDocumento: String = "",
    var idPlato: Int = 0,
    var idCategoria: Int = 0,
    var fecha: Date? = null
) : Serializable {

}