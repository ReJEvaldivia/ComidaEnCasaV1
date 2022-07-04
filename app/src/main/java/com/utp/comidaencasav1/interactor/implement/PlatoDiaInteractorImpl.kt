package com.utp.comidaencasav1.interactor.implement

import com.utp.comidaencasav1.interactor.interfaces.PlatoDiaInteractor
import com.utp.comidaencasav1.model.Usuario
import com.utp.comidaencasav1.presenter.interfaces.PlatoDiaPresenter
import com.utp.comidaencasav1.repository.implement.PlatoDiaRepositoryImpl
import com.utp.comidaencasav1.repository.interfaces.PlatoDiaRepository

class PlatoDiaInteractorImpl(var platoDiaPresenter: PlatoDiaPresenter) : PlatoDiaInteractor {
    private var platoDiaRepository: PlatoDiaRepository = PlatoDiaRepositoryImpl(platoDiaPresenter)

    override fun getPlatosSugerenciaFirebase(usuario: Usuario) {
        platoDiaRepository.getPlatosSugerenciaFirebase(usuario)
    }
}