package com.utp.comidaencasav1.interactor.interfaces

import com.utp.comidaencasav1.model.Cuenta

interface CuentaInteractor {
    fun getCuentaFirebase(cuenta: Cuenta)
    fun setCuentaFirebase(cuenta: Cuenta)
}