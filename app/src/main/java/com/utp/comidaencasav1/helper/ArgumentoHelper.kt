package com.utp.comidaencasav1.helper

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.utp.comidaencasav1.model.Insumo
import com.utp.comidaencasav1.model.Plato
import com.utp.comidaencasav1.model.Usuario
import java.io.Serializable

class ArgumentoHelper {
    /**
     * Declarar argumento a otro fragment
     * */
    fun setArgPlato(plato: Plato?): Bundle {
        return bundleOf("arg_plato" to plato)
    }

    /**
     * Recuperar argumento
     * */
    fun getArgPlato(arguments: Bundle?): Serializable? {
        return arguments?.getSerializable("arg_plato")
    }

    fun setArgInsumo(insumo: Insumo?): Bundle {
        return bundleOf("arg_insumo" to insumo)
    }

    fun getArgInsumo(arguments: Bundle?): Serializable? {
        return arguments?.getSerializable("arg_insumo")
    }

    fun setArgUsuario(usuario: Usuario?): Bundle {
        return bundleOf("arg_usuario" to usuario)
    }
}