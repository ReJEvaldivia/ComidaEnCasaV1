package com.utp.comidaencasav1.view.interfaces

import com.utp.comidaencasav1.model.Usuario

interface UsuarioView {
    //Vista
    fun showUsuarioDefault(usuario: Usuario)
    //fun showUsuarios(usuarios: ArrayList<Usuario>)

    //Navegar al Fragment usuarios
    //fun navigateUsuarios()

    //Presentador
    fun getUsuarioDefault()
    //fun getUsuarios(idUsuarioCreador: Int)
    //fun setUsuario(usuario: Usuario)
    //fun updateUsuario(usuario: Usuario)
    //fun deleteUsuario(idDocumento: String)
}
