package com.utp.comidaencasav1.view.interfaces

import com.utp.comidaencasav1.model.Usuario

interface UsuarioView {
    //Vista
    fun showUsuarios(usuarios: ArrayList<Usuario>)
    fun showUsuarioDefault(usuario: Usuario)

    //Navegar al Fragment usuarios
    fun navigateNavUsuarios()

    //Presentador
    fun getUsuarios(idUsuarioCreador: Int)
    fun getUsuarioDefault()
    fun setUsuario(usuario: Usuario)
    fun updateUsuario(usuario: Usuario)
    fun deleteUsuario(idDocumento: String)
}
