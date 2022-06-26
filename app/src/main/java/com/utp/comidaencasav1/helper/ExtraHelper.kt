package com.utp.comidaencasav1.helper

import androidx.fragment.app.FragmentActivity
import com.utp.comidaencasav1.model.Usuario

class ExtraHelper {
    fun getExtUsuario(requireActivity: FragmentActivity): Usuario {
        //Recuperar el usuario
        return requireActivity.intent.extras!!.get("ext_usuario") as Usuario
    }
}