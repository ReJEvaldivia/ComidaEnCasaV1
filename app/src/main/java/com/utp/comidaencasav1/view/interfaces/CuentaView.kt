package com.utp.comidaencasav1.view.interfaces

import com.utp.comidaencasav1.model.Cuenta

interface CuentaView {
    fun validateInicioSesion(cuenta: Cuenta?)
    fun navigateLoginActivity()
}