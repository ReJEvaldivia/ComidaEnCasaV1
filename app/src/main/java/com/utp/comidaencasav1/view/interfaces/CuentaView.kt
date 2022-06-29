package com.utp.comidaencasav1.view.interfaces

import com.utp.comidaencasav1.model.Cuenta

interface CuentaView {
    //Métodos Libres:
    fun validateInicioSesion(cuenta: Cuenta?)

    //Navegación:
    fun navigateLoginActivity()

    //CRUD:
    fun getCuenta(cuenta: Cuenta)
    fun setCuenta(cuenta: Cuenta)
    /*
    fun updateCuenta(cuenta: Cuenta)
    fun deleteCuenta(idDocumento: String)*/
}