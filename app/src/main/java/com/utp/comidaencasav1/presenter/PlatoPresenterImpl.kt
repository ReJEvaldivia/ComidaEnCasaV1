package com.utp.comidaencasav1.presenter

import com.utp.comidaencasav1.model.interactors.PlatosInteractor
import com.utp.comidaencasav1.model.interactors.PlatosInteractorImpl
import com.utp.comidaencasav1.model.models.Plato
import com.utp.comidaencasav1.view.PlatoView

class PlatoPresenterImpl (var platoView: PlatoView): PlatoPresenter{

    private var platoInteractor: PlatosInteractor = PlatosInteractorImpl(this)

    override fun showPlatos(platos: ArrayList<Plato>?) {
        platoView.showPlatos(platos)
    }

    override fun getPlatos() {
        platoInteractor.getPlatosFirebase()
    }
}