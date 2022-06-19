package com.utp.comidaencasav1.presenter.implement

import com.utp.comidaencasav1.interactor.interfaces.PlatosInteractor
import com.utp.comidaencasav1.interactor.implement.PlatosInteractorImpl
import com.utp.comidaencasav1.model.Plato
import com.utp.comidaencasav1.presenter.interfaces.PlatoPresenter
import com.utp.comidaencasav1.view.interfaces.PlatoView

class PlatoPresenterImpl (var platoView: PlatoView): PlatoPresenter {

    private var platoInteractor: PlatosInteractor = PlatosInteractorImpl(this)

    override fun showPlatos(platos: ArrayList<Plato>?) {
        platoView.showPlatos(platos)
    }

    override fun getPlatos(idUsuarioCreador: Int) {
        platoInteractor.getPlatosFirebase(idUsuarioCreador)
    }
}