package com.utp.comidaencasav1.view

import com.utp.comidaencasav1.model.models.Plato

interface PlatoView {
    //Vista
    fun showPlatos(platos: ArrayList<Plato>?)

    //Presentador
    fun getPlatos()
}