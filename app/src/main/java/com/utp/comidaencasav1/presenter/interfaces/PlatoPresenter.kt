package com.utp.comidaencasav1.presenter.interfaces

import com.utp.comidaencasav1.model.Plato

interface PlatoPresenter {
    //Vista
    fun showPlatos(platos: ArrayList<Plato>?)

    //Interactor
    fun getPlatos(idUsuarioCreador: Int)
}