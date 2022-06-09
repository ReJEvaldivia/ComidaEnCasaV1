package com.utp.comidaencasav1.model.models

import java.io.Serializable

class Usuario(
    var idDocumento: String = "",
    var idUsuario: Int = 0,
    var idCuenta: Int = 0,
    var nombre: String = "",
    var idRol: Int = 0
) : Serializable {

}