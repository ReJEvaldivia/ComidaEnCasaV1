package com.utp.comidaencasav1.repository.interfaces

import com.utp.comidaencasav1.model.Plato

interface PlatoRepository {
    /**
     * GET: Recupera una lista filtrada
     * */
    fun getPlatosFirebase(idUsuarioCreador: Int)
    /**
     * INSERT
     * */
    fun setPlatoFirebase(plato: Plato)
    /**
     * UPDATE
     */
    fun updatePlatoFirebase(plato: Plato)
    /**
     * DELETE
     */
    fun deletePlatoFirebase(idDocumento: String)
}