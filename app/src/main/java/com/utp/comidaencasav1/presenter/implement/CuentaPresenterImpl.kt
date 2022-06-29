package com.utp.comidaencasav1.presenter.implement

import com.utp.comidaencasav1.interactor.implement.CuentaInteractorImpl
import com.utp.comidaencasav1.interactor.interfaces.CuentaInteractor
import com.utp.comidaencasav1.model.Cuenta
import com.utp.comidaencasav1.presenter.interfaces.CuentaPresenter
import com.utp.comidaencasav1.view.interfaces.CuentaView

class CuentaPresenterImpl(var cuentaView: CuentaView) : CuentaPresenter {

    private var cuentaInteractor: CuentaInteractor = CuentaInteractorImpl(this)

    override fun validateInicioSesion(cuenta: Cuenta?) {
        cuentaView.validateInicioSesion(cuenta)
    }

    override fun getCuenta(cuenta: Cuenta) {
        cuentaInteractor.getCuentaFirebase(cuenta)
    }

    /*
    override fun showCuentaLogin(cuentas: ArrayList<Cuenta>) {
        cuentaView.showCuentas(cuentas)
    }

    override fun navigateNavCuentas() {
        cuentaView.navigateNavCuentas()
    }

    override fun setCuenta(cuenta: Cuenta) {
        cuentaInteractor.setCuentaFirebase(cuenta)
    }

    override fun updateCuenta(cuenta: Cuenta) {
        cuentaInteractor.updateCuentaFirebase(cuenta)
    }

    override fun deleteCuenta(idDocumento: String) {
        cuentaInteractor.deleteCuentaFirebase(idDocumento)
    }
    */
}