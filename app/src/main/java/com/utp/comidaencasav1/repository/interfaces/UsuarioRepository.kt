package com.utp.comidaencasav1.repository.interfaces

import com.utp.comidaencasav1.model.Usuario

interface UsuarioRepository {
    /**
     * Recupera el usuario por defecto de firebase
     * */
    fun getUsuarioDefaultFirebase()
    fun getUsuariosFirebase(idCuenta: Int)
    fun setUsuarioFirebase(usuario: Usuario)
}