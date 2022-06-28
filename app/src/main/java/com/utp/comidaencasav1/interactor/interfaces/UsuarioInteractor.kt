package com.utp.comidaencasav1.interactor.interfaces

import com.utp.comidaencasav1.model.Usuario

interface UsuarioInteractor {
    fun getUsuariosFirebase(idUsuarioCreador: Int)
    fun getUsuarioDefaultFirebase()
    fun setUsuarioFirebase(usuario: Usuario)
    fun updateUsuarioFirebase(usuario: Usuario)
    fun deleteUsuarioFirebase(idDocumento: String)
}