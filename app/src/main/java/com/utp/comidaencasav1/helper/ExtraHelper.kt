package com.utp.comidaencasav1.helper

import android.content.Context
import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.utp.comidaencasav1.model.Cuenta
import com.utp.comidaencasav1.model.Usuario
import com.utp.comidaencasav1.view.activity.MainActivity

class ExtraHelper {

    fun getExtUsuario(requireActivity: FragmentActivity): Usuario {
        //Recuperar el usuario
        val bundle = OperacionHelper().getBundle(requireActivity)
        return bundle!!.getSerializable("ext_usuario") as Usuario
    }

    fun setExtUsuario(context: Context?, usuario: Usuario, clazz: Class<*>): Intent {
        //Declarar extra a otro activity
        val it = Intent(context, clazz)
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        it.putExtra("ext_usuario", usuario)
        return it
    }

    fun setExtCuenta(context: Context?, cuenta: Cuenta, clazz: Class<*>): Intent {
        //Declarar extra a otro activity
        val it = Intent(context, clazz)
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        it.putExtra("ext_cuenta", cuenta)
        return it
    }
}