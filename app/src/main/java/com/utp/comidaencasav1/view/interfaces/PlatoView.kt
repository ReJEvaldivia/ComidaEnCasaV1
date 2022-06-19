package com.utp.comidaencasav1.view.interfaces

import com.utp.comidaencasav1.model.Plato

interface PlatoView {
    //Vista
    fun showPlatos(platos: ArrayList<Plato>?)

    //Presentador
    fun getPlatos(idUsuarioCreador: Int)
}