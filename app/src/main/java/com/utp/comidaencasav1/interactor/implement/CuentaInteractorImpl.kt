package com.utp.comidaencasav1.interactor.implement

import com.utp.comidaencasav1.interactor.interfaces.CuentaInteractor
import com.utp.comidaencasav1.model.Cuenta
import com.utp.comidaencasav1.presenter.interfaces.CuentaPresenter
import com.utp.comidaencasav1.repository.implement.CuentaRepositoryImpl
import com.utp.comidaencasav1.repository.interfaces.CuentaRepository

class CuentaInteractorImpl(var cuentaPresenter: CuentaPresenter): CuentaInteractor {
    private var cuentaRepository: CuentaRepository = CuentaRepositoryImpl(cuentaPresenter)

    override fun getCuentaFirebase(cuenta: Cuenta) {
        cuentaRepository.getCuentaFirebase(cuenta)
    }

    override fun setCuentaFirebase(cuenta: Cuenta) {
        cuentaRepository.setCuentaFirebase(cuenta)
    }
/*
    override fun updateCuentaFirebase(cuenta: Cuenta) {
        cuentaRepository.updateCuentaFirebase(cuenta)
    }

    override fun deleteCuentaFirebase(idDocumento: String) {
        cuentaRepository.deleteCuentaFirebase(idDocumento)
    }*/
}