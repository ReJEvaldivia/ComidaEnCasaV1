package com.utp.comidaencasav1.presenter.interfaces

import com.utp.comidaencasav1.model.Usuario

interface UsuarioPresenter {
    fun showUsuarioDefault(usuario: Usuario)
    fun showPerfiles(usuarios: List<Usuario>)
    fun navigatePerfilActivity()
    fun getUsuarioDefault()
    fun getUsuarios(idCuenta: Int)
    fun setUsuario(usuario: Usuario)
}