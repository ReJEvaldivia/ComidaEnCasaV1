package com.utp.comidaencasav1.interactor.implement

import com.utp.comidaencasav1.interactor.interfaces.PlatosInteractor
import com.utp.comidaencasav1.model.Plato
import com.utp.comidaencasav1.repository.interfaces.PlatoRepository
import com.utp.comidaencasav1.repository.implement.PlatoRepositoryImpl
import com.utp.comidaencasav1.presenter.interfaces.PlatoPresenter

class PlatosInteractorImpl(var platoPresenter: PlatoPresenter): PlatosInteractor {
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