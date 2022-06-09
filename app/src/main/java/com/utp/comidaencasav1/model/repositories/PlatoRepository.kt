package com.utp.comidaencasav1.model.repositories

import com.utp.comidaencasav1.model.models.Usuario
import com.utp.comidaencasav1.view.PlatoView

interface PlatoRepository {
    fun getPlatosFirebase(idUsuarioCreador: Int)
}