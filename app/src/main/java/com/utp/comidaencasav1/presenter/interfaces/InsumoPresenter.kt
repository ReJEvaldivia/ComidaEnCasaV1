package com.utp.comidaencasav1.presenter.interfaces

import com.utp.comidaencasav1.model.Insumo

interface InsumoPresenter{
    //Visualización: Hace referencia al adapter
    fun showInsumos(insumos: ArrayList<Insumo>)

    //Navegación:
    fun navigateInsumosFragment()

    //CRUD:
    fun getInsumos(idCuenta: Int)
    fun setInsumo(insumo: Insumo)
    fun updateInsumo(insumo: Insumo)
    fun deleteInsumo(idDocumento: String)
}