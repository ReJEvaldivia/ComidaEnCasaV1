package com.utp.comidaencasav1.interactor.interfaces

import com.utp.comidaencasav1.model.Usuario

interface UsuarioInteractor {
    fun getUsuarioDefaultFirebase()
    fun getUsuariosFirebase(idCuenta: Int)
    fun setUsuarioFirebase(usuario: Usuario)
}