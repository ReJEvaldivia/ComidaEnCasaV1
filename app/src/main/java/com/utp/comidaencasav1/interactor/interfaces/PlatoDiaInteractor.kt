package com.utp.comidaencasav1.interactor.interfaces

import com.utp.comidaencasav1.model.Usuario

interface PlatoDiaInteractor {
    fun getPlatosSugerenciaFirebase(usuario: Usuario)
}