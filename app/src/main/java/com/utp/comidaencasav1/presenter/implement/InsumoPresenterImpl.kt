package com.utp.comidaencasav1.presenter.implement

import com.utp.comidaencasav1.interactor.implement.InsumoInteractorImpl
import com.utp.comidaencasav1.interactor.interfaces.InsumoInteractor
import com.utp.comidaencasav1.model.Insumo
import com.utp.comidaencasav1.presenter.interfaces.InsumoPresenter
import com.utp.comidaencasav1.view.interfaces.InsumoView

class InsumoPresenterImpl(var insumoView: InsumoView): InsumoPresenter {

    private var insumoInteractor: InsumoInteractor = InsumoInteractorImpl(this)

    override fun showInsumos(insumos: ArrayList<Insumo>) {
        insumoView.showInsumos(insumos)
    }

    override fun navigateInsumosFragment() {
        insumoView.navigateInsumosFragment()
    }

    override fun getInsumos(idCuenta: Int) {
        insumoInteractor.getInsumosFirebase(idCuenta)
    }

    override fun setInsumo(insumo: Insumo) {
        insumoInteractor.setInsumoFirebase(insumo)
    }

    override fun updateInsumo(insumo: Insumo) {
        insumoInteractor.updateInsumoFirebase(insumo)
    }

    override fun deleteInsumo(idDocumento: String) {
        insumoInteractor.deleteInsumoFirebase(idDocumento)
    }
}