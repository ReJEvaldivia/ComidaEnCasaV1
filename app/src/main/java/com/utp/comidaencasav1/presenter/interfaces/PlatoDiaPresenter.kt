package com.utp.comidaencasav1.presenter.interfaces

import com.utp.comidaencasav1.model.Plato
import com.utp.comidaencasav1.model.Usuario

interface PlatoDiaPresenter {
    //Métodos Libres:
    fun listPlatosSugerencia(platos: List<Plato>)

    fun getPlatosSugerencia(usuario: Usuario)
}