package com.utp.comidaencasav1.helper

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.utp.comidaencasav1.model.Plato
import java.io.Serializable

class ArgumentoHelper {
    fun setArgPlato(plato: Plato?): Bundle {
        //Declarar argumentos a otro fragment
        return bundleOf("arg_plato" to plato)
    }

    fun getArgPlato(arguments: Bundle?): Serializable? {
        //Recuperar el item
        return arguments?.getSerializable("arg_plato")
    }
}