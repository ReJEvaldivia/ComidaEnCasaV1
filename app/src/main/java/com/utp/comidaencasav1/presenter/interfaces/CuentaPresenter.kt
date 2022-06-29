package com.utp.comidaencasav1.presenter.interfaces

import com.utp.comidaencasav1.model.Cuenta

interface CuentaPresenter {
    //Vista
    fun validateInicioSesion(cuenta: Cuenta?)
    /*fun showCuentas(cuentas: ArrayList<Cuenta>)
    fun navigateNavCuentas()
    fun showCuentaLogin(esCorrecto: Boolean)*/

    //Interactor
    fun getCuenta(cuenta: Cuenta)
    /*fun setCuenta(cuenta: Cuenta)
    fun updateCuenta(cuenta: Cuenta)
    fun deleteCuenta(idDocumento: String)*/
}