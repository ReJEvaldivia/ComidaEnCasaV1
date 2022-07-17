package com.utp.comidaencasav1.view.interfaces

import com.utp.comidaencasav1.model.Plato

interface PlatoView {
    fun showPlatos(platos: List<Plato>)
    fun navigatePlatosFragment()
}