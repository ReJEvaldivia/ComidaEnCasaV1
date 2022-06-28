package com.utp.comidaencasav1.presenter.interfaces

import com.utp.comidaencasav1.model.Usuario

interface UsuarioPresenter {
    //Vista
    fun showUsuarios(usuarios: ArrayList<Usuario>)
    fun showUsuarioDefault(usuario: Usuario)
    fun navigateNavUsuarios()

    //Interactor
    fun getUsuarios(idUsuarioCreador: Int)
    fun getUsuarioDefault()
    fun setUsuario(usuario: Usuario)
    fun updateUsuario(usuario: Usuario)
    fun deleteUsuario(idDocumento: String)
}