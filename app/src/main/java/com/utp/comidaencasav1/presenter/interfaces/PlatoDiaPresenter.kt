package com.utp.comidaencasav1.presenter.interfaces

import com.utp.comidaencasav1.model.Plato
import com.utp.comidaencasav1.model.PlatoDia
import com.utp.comidaencasav1.model.Usuario

interface PlatoDiaPresenter {
    //Métodos Libres:
    fun setPlatoSugerencia(idCuenta: Int)
    fun listPlatosSugerencia(platos: List<Plato>)
    fun successSetPlatoDia(success: Boolean, platoDia: PlatoDia)
    fun existsPlatosSugerenciaFirebase(exists: Boolean)

}