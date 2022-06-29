package com.utp.comidaencasav1.repository.interfaces

import com.utp.comidaencasav1.model.Cuenta

interface CuentaRepository {
    fun getCuentaFirebase(cuenta: Cuenta)
    fun setCuentaFirebase(cuenta: Cuenta)
    /*
    fun updateCuentaFirebase(cuenta: Cuenta)
    fun deleteCuentaFirebase(idDocumento: String)*/
}