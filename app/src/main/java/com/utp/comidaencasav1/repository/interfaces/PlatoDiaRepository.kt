package com.utp.comidaencasav1.repository.interfaces

import com.utp.comidaencasav1.model.Usuario

interface PlatoDiaRepository {
    fun getPlatosSugerenciaFirebase(usuario: Usuario)
}