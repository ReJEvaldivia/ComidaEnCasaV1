package com.utp.comidaencasav1.view.interfaces

import com.utp.comidaencasav1.model.Plato

interface PlatoView {
    //Vista: Hace referencia al adapter
    fun showPlatos(platos: ArrayList<Plato>)

    //Navegar:
    fun navigatePlatosFragment()

    //Presentador:
    fun getPlatos(idUsuarioCreador: Int)
    fun setPlato(plato: Plato)
    fun updatePlato(plato: Plato)
    fun deletePlato(idDocumento: String)
}