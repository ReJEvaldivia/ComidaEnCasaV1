package com.utp.comidaencasav1.model.interactors

import com.utp.comidaencasav1.model.repositories.PlatoRepository
import com.utp.comidaencasav1.model.repositories.PlatoRepositoryImpl
import com.utp.comidaencasav1.presenter.PlatoPresenter

class PlatosInteractorImpl(var platoPresenter: PlatoPresenter): PlatosInteractor {
    private var platoRepository: PlatoRepository = PlatoRepositoryImpl(platoPresenter)

    override fun getPlatosFirebase() {
        platoRepository.getPlatosFirebase()
    }

}