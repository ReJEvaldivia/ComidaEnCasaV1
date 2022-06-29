package com.utp.comidaencasav1.interactor.interfaces

import com.utp.comidaencasav1.model.Plato

interface PlatoInteractor {
    fun getPlatosFirebase(idUsuarioCreador: Int)
    fun setPlatoFirebase(plato: Plato)
    fun updatePlatoFirebase(plato: Plato)
    fun deletePlatoFirebase(idDocumento: String)
}