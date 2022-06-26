package com.utp.comidaencasav1.repository.interfaces

import com.utp.comidaencasav1.model.Plato

interface PlatoRepository {
    fun getPlatosFirebase(idUsuarioCreador: Int)
    fun setPlatoFirebase(plato: Plato)
    fun updatePlatoFirebase(plato: Plato)
    fun deletePlatoFirebase(idDocumento: String)
}