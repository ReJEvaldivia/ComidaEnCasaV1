package com.utp.comidaencasav1.interactor.interfaces

import com.utp.comidaencasav1.model.Plato
import com.utp.comidaencasav1.model.Usuario

interface PlatoDiaInteractor {
    fun getPlatosSugerenciaFirebase(idCuenta: Int)
    fun setPlatoSugerenciaFirebase(platos: List<Plato>)
    fun existsPlatosSugerenciaFirebase(idCuenta: Int)
}