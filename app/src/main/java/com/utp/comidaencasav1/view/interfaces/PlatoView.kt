package com.utp.comidaencasav1.view.interfaces

import com.utp.comidaencasav1.model.Plato

interface PlatoView {
    //Vista
    fun showPlatos(platos: ArrayList<Plato>)

    //Navegar al Fragment platos
    fun navigateNavPlatos()

    //Presentador
    fun getPlatos(idUsuarioCreador: Int)
    fun setPlato(plato: Plato)
    fun updatePlato(plato: Plato)
    fun deletePlato(idDocumento: String)
}