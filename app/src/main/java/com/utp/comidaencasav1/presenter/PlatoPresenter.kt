package com.utp.comidaencasav1.presenter

import com.utp.comidaencasav1.model.models.Plato

interface PlatoPresenter {
    //Vista
    fun showPlatos(platos: ArrayList<Plato>?)

    //Interactor
    fun getPlatos(idUsuarioCreador: Int)
}