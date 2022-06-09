package com.utp.comidaencasav1.model.models

import java.io.Serializable
import java.util.*

class Cuenta(
    var idDocumento: String = "",
    var idCuenta: Int = 0,
    var familiaNombre: String = "",
    var correo: String = "",
    var contrasena: String = ""
) : Serializable {

}