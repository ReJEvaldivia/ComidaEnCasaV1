package com.utp.comidaencasav1.presenter.interfaces

import com.utp.comidaencasav1.model.Usuario

interface UsuarioPresenter {
    //Vista
    fun showUsuarioDefault(usuario: Usuario)

    //fun showUsuarios(usuarios: ArrayList<Usuario>)
    //fun navigateNavUsuarios()

    //Interactor
    fun getUsuarioDefault()
    //fun getUsuarios(idUsuarioCreador: Int)
    /*fun setUsuario(usuario: Usuario)
    fun updateUsuario(usuario: Usuario)
    fun deleteUsuario(idDocumento: String)*/
}