package com.utp.comidaencasav1.view.interfaces

import com.utp.comidaencasav1.model.Cuenta

interface CuentaView {
    //Vista
    fun validateInicioSesion(cuenta: Cuenta?)

    //Navegar al Fragment cuentas
    fun navigateLoginActivity()

    //Presentador
    fun getCuenta(cuenta: Cuenta)
    fun setCuenta(cuenta: Cuenta)
    /*
    fun updateCuenta(cuenta: Cuenta)
    fun deleteCuenta(idDocumento: String)*/
}