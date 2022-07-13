package com.utp.comidaencasav1.presenter.implement

import com.utp.comidaencasav1.interactor.implement.PlatoDiaInteractorImpl
import com.utp.comidaencasav1.interactor.interfaces.PlatoDiaInteractor
import com.utp.comidaencasav1.model.Plato
import com.utp.comidaencasav1.model.PlatoDia
import com.utp.comidaencasav1.model.Usuario
import com.utp.comidaencasav1.presenter.interfaces.PlatoDiaPresenter
import com.utp.comidaencasav1.service.Service

class PlatoDiaPresenterImpl(/*var platoDiaView: PlatoDiaView*/) : PlatoDiaPresenter {

    private var platoDiaInteractor: PlatoDiaInteractor = PlatoDiaInteractorImpl(this)
    private var service: Service = Service()
    private var idCuenta: Int? = null

    override fun setPlatoSugerencia(idCuenta2: Int) {
        idCuenta = idCuenta2
        platoDiaInteractor.existsPlatosSugerenciaFirebase(idCuenta!!)
    }

    override fun listPlatosSugerencia(platos: List<Plato>) {
        platoDiaInteractor.setPlatoSugerenciaFirebase(platos)
    }

    override fun successSetPlatoDia(success: Boolean, platoDia: PlatoDia) {
        service.successSetPlatoDia(success, platoDia)
    }

    override fun existsPlatosSugerenciaFirebase(exists: Boolean) {
        if (exists) {
            service.successSetPlatoDia(!exists)
        } else {
            platoDiaInteractor.getPlatosSugerenciaFirebase(idCuenta!!)
        }
    }

}