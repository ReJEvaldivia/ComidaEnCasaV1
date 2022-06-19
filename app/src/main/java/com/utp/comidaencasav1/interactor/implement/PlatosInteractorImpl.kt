package com.utp.comidaencasav1.interactor.implement

import com.utp.comidaencasav1.interactor.interfaces.PlatosInteractor
import com.utp.comidaencasav1.repository.interfaces.PlatoRepository
import com.utp.comidaencasav1.repository.implement.PlatoRepositoryImpl
import com.utp.comidaencasav1.presenter.interfaces.PlatoPresenter

class PlatosInteractorImpl(var platoPresenter: PlatoPresenter): PlatosInteractor {
    private var platoRepository: PlatoRepository = PlatoRepositoryImpl(platoPresenter)

    override fun getPlatosFirebase(idUsuarioCreador: Int) {
        platoRepository.getPlatosFirebase(idUsuarioCreador)
    }

}