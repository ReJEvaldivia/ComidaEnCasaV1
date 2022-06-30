package com.utp.comidaencasav1.presenter.interfaces

import com.utp.comidaencasav1.model.Cuenta

interface CuentaPresenter {
    //Métodos Libres:
    fun validateInicioSesion(cuenta: Cuenta?)

    fun navigateLoginActivity()
    fun getCuenta(cuenta: Cuenta)
    fun setCuenta(cuenta: Cuenta)
}