package com.utp.comidaencasav1.repository.interfaces

import com.utp.comidaencasav1.model.Insumo

interface InsumoRepository {
    /**
     * GET: Recupera una lista filtrada
     * */
    fun getInsumosFirebase(idCuenta: Int)
    /**
     * INSERT
     * */
    fun setInsumoFirebase(insumo: Insumo)
    /**
     * UPDATE
     */
    fun updateInsumoFirebase(insumo: Insumo)
    /**
     * DELETE
     */
    fun deleteInsumoFirebase(idDocumento: String)
}