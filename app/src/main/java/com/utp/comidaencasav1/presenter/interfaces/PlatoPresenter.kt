package com.utp.comidaencasav1.presenter.interfaces

import com.utp.comidaencasav1.model.Plato

interface PlatoPresenter {
    //Vista
    fun showPlatos(platos: ArrayList<Plato>)
    fun navigateNavPlatos()

    //Interactor
    fun getPlatos(idUsuarioCreador: Int)
    fun setPlato(plato: Plato)
    fun updatePlato(plato: Plato)
    fun deletePlato(idDocumento: String)
}