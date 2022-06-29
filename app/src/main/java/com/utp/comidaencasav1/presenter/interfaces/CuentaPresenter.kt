package com.utp.comidaencasav1.presenter.interfaces

import com.utp.comidaencasav1.model.Cuenta

interface CuentaPresenter {
    fun validateInicioSesion(cuenta: Cuenta?)
    fun navigateLoginActivity()
    fun getCuenta(cuenta: Cuenta)
    fun setCuenta(cuenta: Cuenta)
    /*fun showCuentas(cuentas: ArrayList<Cuenta>)
    fun navigateNavCuentas()
    fun showCuentaLogin(esCorrecto: Boolean)
    fun updateCuenta(cuenta: Cuenta)
    fun deleteCuenta(idDocumento: String)*/
}