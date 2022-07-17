package com.utp.comidaencasav1.view.interfaces

import com.utp.comidaencasav1.model.Insumo

interface InsumoView {
    fun showInsumos(insumos: List<Insumo>)
    fun navigateInsumosFragmentOPlatosDetalleFragment()
}