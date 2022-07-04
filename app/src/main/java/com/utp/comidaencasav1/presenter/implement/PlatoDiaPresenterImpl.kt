package com.utp.comidaencasav1.presenter.implement

import com.utp.comidaencasav1.interactor.implement.PlatoDiaInteractorImpl
import com.utp.comidaencasav1.interactor.interfaces.PlatoDiaInteractor
import com.utp.comidaencasav1.model.Plato
import com.utp.comidaencasav1.model.Usuario
import com.utp.comidaencasav1.presenter.interfaces.PlatoDiaPresenter
import com.utp.comidaencasav1.service.Service

class PlatoDiaPresenterImpl(/*var platoDiaView: PlatoDiaView*/) : PlatoDiaPresenter {

    private var platoDiaInteractor: PlatoDiaInteractor = PlatoDiaInteractorImpl(this)
    private var service: Service = Service()

    override fun listPlatosSugerencia(platos: List<Plato>) {
        service.listPlatosSugerencia(platos)
    }

    override fun getPlatosSugerencia(usuario: Usuario) {
        platoDiaInteractor.getPlatosSugerenciaFirebase(usuario)
    }
}