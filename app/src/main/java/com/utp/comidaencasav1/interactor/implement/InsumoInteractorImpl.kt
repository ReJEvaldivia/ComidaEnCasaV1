package com.utp.comidaencasav1.interactor.implement

import com.utp.comidaencasav1.interactor.interfaces.InsumoInteractor
import com.utp.comidaencasav1.model.Insumo
import com.utp.comidaencasav1.presenter.interfaces.InsumoPresenter
import com.utp.comidaencasav1.repository.implement.InsumoRepositoryImpl
import com.utp.comidaencasav1.repository.interfaces.InsumoRepository

class InsumoInteractorImpl(var insumoPresenter: InsumoPresenter) : InsumoInteractor {
    private var insumoRepository: InsumoRepository = InsumoRepositoryImpl(insumoPresenter)

    override fun getInsumosFirebase(idCuenta: Int) {
        insumoRepository.getInsumosFirebase(idCuenta)
    }

    override fun setInsumoFirebase(insumo: Insumo) {
        insumoRepository.setInsumoFirebase(insumo)
    }

    override fun updateInsumoFirebase(insumo: Insumo) {
        insumoRepository.updateInsumoFirebase(insumo)
    }

    override fun deleteInsumoFirebase(idDocumento: String) {
        insumoRepository.deleteInsumoFirebase(idDocumento)
    }
}