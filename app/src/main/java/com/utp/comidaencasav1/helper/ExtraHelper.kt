package com.utp.comidaencasav1.helper

import android.content.Context
import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.utp.comidaencasav1.model.Cuenta
import com.utp.comidaencasav1.model.Usuario

class ExtraHelper {

    /**
     * Recuperar el usuario
     * */
    fun getExtUsuario(requireActivity: FragmentActivity): Usuario {
        val bundle = OperacionHelper().getBundle(requireActivity)
        return bundle!!.getSerializable("ext_usuario") as Usuario
    }

    /**
     * Declarar extra a otro activity
     * */
    fun setExtUsuario(context: Context?, usuario: Usuario, clazz: Class<*>): Intent {
        val it = Intent(context, clazz)
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        it.putExtra("ext_usuario", usuario)
        return it
    }

    fun setExtCuenta(context: Context?, cuenta: Cuenta, clazz: Class<*>): Intent {
        val it = Intent(context, clazz)
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        it.putExtra("ext_cuenta", cuenta)
        return it
    }

    /**
     * Recuperar la cuenta
     * */
    fun getExtCuenta(requireActivity: FragmentActivity): Cuenta {
        val bundle = OperacionHelper().getBundle(requireActivity)
        return bundle!!.getSerializable("ext_cuenta") as Cuenta
    }
}