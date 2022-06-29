package com.utp.comidaencasav1.repository.interfaces

import com.utp.comidaencasav1.model.Usuario

interface UsuarioRepository {
    /**
     * Recupera el usuario por defecto de firebase
     * */
    fun getUsuarioDefaultFirebase()
    //fun getUsuariosFirebase(idUsuarioCreador: Int)
    /*fun setUsuarioFirebase(usuario: Usuario)
    fun updateUsuarioFirebase(usuario: Usuario)
    fun deleteUsuarioFirebase(idDocumento: String)*/
}