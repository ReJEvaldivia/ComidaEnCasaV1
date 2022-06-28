package com.utp.comidaencasav1.repository.interfaces

import com.utp.comidaencasav1.model.Usuario

interface UsuarioRepository {
    fun getUsuariosFirebase(idUsuarioCreador: Int)
    fun getUsuarioDefaultFirebase()
    fun setUsuarioFirebase(usuario: Usuario)
    fun updateUsuarioFirebase(usuario: Usuario)
    fun deleteUsuarioFirebase(idDocumento: String)
}