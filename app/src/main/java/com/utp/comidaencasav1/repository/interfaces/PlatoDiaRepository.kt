package com.utp.comidaencasav1.repository.interfaces

import com.utp.comidaencasav1.model.PlatoDia
import com.utp.comidaencasav1.model.Usuario

interface PlatoDiaRepository {
    fun getPlatosSugerenciaFirebase(idCuenta: Int)
    fun setPlatoDiaFirebase(platoDia: PlatoDia)
    fun existsPlatosSugerenciaFirebase(idCuenta: Int)
}