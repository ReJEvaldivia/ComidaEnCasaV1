package com.utp.comidaencasav1.interactor.interfaces

import com.utp.comidaencasav1.model.Insumo

interface InsumoInteractor {
    fun getInsumosFirebase(idCuenta: Int)
    fun setInsumoFirebase(insumo: Insumo)
    fun updateInsumoFirebase(insumo: Insumo)
    fun deleteInsumoFirebase(idDocumento: String)
}