package com.utp.comidaencasav1.interactor.implement

import com.utp.comidaencasav1.interactor.interfaces.PlatoInteractor
import com.utp.comidaencasav1.model.Plato
import com.utp.comidaencasav1.presenter.interfaces.PlatoPresenter
import com.utp.comidaencasav1.repository.implement.PlatoRepositoryImpl
import com.utp.comidaencasav1.repository.interfaces.PlatoRepository

class PlatoInteractorImpl(var platoPresenter: PlatoPresenter) : PlatoInteractor {
    private var platoRepository: PlatoRepository = PlatoRepositoryImpl(platoPresenter)

    override fun getPlatosFirebase(idUsuarioCreador: Int) {
        platoRepository.getPlatosFirebase(idUsuarioCreador)
    }

    override fun setPlatoFirebase(plato: Plato) {
        platoRepository.setPlatoFirebase(plato)
    }

    override fun updatePlatoFirebase(plato: Plato) {
        platoRepository.updatePlatoFirebase(plato)
    }

    override fun deletePlatoFirebase(idDocumento: String) {
        platoRepository.deletePlatoFirebase(idDocumento)
    }
}