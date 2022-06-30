package com.utp.comidaencasav1.presenter.interfaces

import com.utp.comidaencasav1.model.Plato

interface PlatoPresenter {
    //Visualización: Hace referencia al adapter
    fun showPlatos(platos: ArrayList<Plato>)

    //Navegación:
    fun navigatePlatosFragment()

    //CRUD:
    fun getPlatos(idUsuarioCreador: Int)
    fun setPlato(plato: Plato)
    fun updatePlato(plato: Plato)
    fun deletePlato(idDocumento: String)
}