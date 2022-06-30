package com.utp.comidaencasav1.view.interfaces

import com.utp.comidaencasav1.model.Usuario

interface UsuarioView {
    fun showUsuarioDefault(usuario: Usuario)
    fun navigatePerfilActivity()
    fun showPerfiles(usuarios: List<Usuario>)
    fun getUsuario(): Usuario
    fun getUsuarioDefault()
}
