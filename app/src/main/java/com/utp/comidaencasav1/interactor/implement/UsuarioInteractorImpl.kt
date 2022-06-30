package com.utp.comidaencasav1.interactor.implement

import com.utp.comidaencasav1.interactor.interfaces.UsuarioInteractor
import com.utp.comidaencasav1.model.Usuario
import com.utp.comidaencasav1.presenter.interfaces.UsuarioPresenter
import com.utp.comidaencasav1.repository.implement.UsuarioRepositoryImpl
import com.utp.comidaencasav1.repository.interfaces.UsuarioRepository

class UsuarioInteractorImpl(var usuarioPresenter: UsuarioPresenter): UsuarioInteractor {
    private var usuarioRepository: UsuarioRepository = UsuarioRepositoryImpl(usuarioPresenter)

    override fun getUsuarioDefaultFirebase() {
        usuarioRepository.getUsuarioDefaultFirebase()
    }

    override fun getUsuariosFirebase(idCuenta: Int) {
        usuarioRepository.getUsuariosFirebase(idCuenta)

    }

    override fun setUsuarioFirebase(usuario: Usuario) {
        usuarioRepository.setUsuarioFirebase(usuario)
    }


    /*
    override fun updateUsuarioFirebase(usuario: Usuario) {
        usuarioRepository.updateUsuarioFirebase(usuario)
    }

    override fun deleteUsuarioFirebase(idDocumento: String) {
        usuarioRepository.deleteUsuarioFirebase(idDocumento)
    }*/

}