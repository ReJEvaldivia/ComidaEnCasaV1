package com.utp.comidaencasav1.presenter.implement

import com.utp.comidaencasav1.interactor.implement.UsuarioInteractorImpl
import com.utp.comidaencasav1.interactor.interfaces.UsuarioInteractor
import com.utp.comidaencasav1.model.Usuario
import com.utp.comidaencasav1.presenter.interfaces.UsuarioPresenter
import com.utp.comidaencasav1.view.interfaces.UsuarioView

class UsuarioPresenterImpl (var usuarioView: UsuarioView): UsuarioPresenter {

    private var usuarioInteractor: UsuarioInteractor = UsuarioInteractorImpl(this)

    override fun showUsuarios(usuarios: ArrayList<Usuario>) {
        usuarioView.showUsuarios(usuarios)
    }

    override fun showUsuarioDefault(usuario: Usuario) {
        usuarioView.showUsuarioDefault(usuario)
    }

    override fun navigateNavUsuarios() {
        usuarioView.navigateNavUsuarios()
    }

    override fun getUsuarios(idUsuarioCreador: Int) {
        usuarioInteractor.getUsuariosFirebase(idUsuarioCreador)
    }

    override fun getUsuarioDefault() {
        usuarioInteractor.getUsuarioDefaultFirebase()
    }

    override fun setUsuario(usuario: Usuario) {
        usuarioInteractor.setUsuarioFirebase(usuario)
    }

    override fun updateUsuario(usuario: Usuario) {
        usuarioInteractor.updateUsuarioFirebase(usuario)
    }

    override fun deleteUsuario(idDocumento: String) {
        usuarioInteractor.deleteUsuarioFirebase(idDocumento)
    }
}