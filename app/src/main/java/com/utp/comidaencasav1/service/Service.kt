package com.utp.comidaencasav1.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log
import com.utp.comidaencasav1.helper.ExtraHelper
import com.utp.comidaencasav1.model.Plato
import com.utp.comidaencasav1.model.PlatoDia
import com.utp.comidaencasav1.model.Usuario
import com.utp.comidaencasav1.presenter.implement.PlatoDiaPresenterImpl
import com.utp.comidaencasav1.presenter.implement.PlatoPresenterImpl
import com.utp.comidaencasav1.repository.implement.PlatoRepositoryImpl
import java.text.SimpleDateFormat
import java.util.*


class Service() : Service() {
    private val TAG = "service"
    val handler = Handler()
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "start")
        var contador = 0
        var time: Long = 10000//Equivale a 10 segundos
        var dd = Date()
        //val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val sdf = SimpleDateFormat("hh")
        handler.apply {
            val runnable = object : Runnable {
                override fun run() {
                    contador++
                    Log.d(TAG, "el valor contador: $contador")
                    var hour = sdf.format(Date()).toInt()
                    //if (hour == 44) {//Cuando sea las 00 horas
                    Log.d(TAG, "hora actual: $hour")
                    val bundle = intent!!.extras
                    val usuario = bundle!!.getSerializable("ext_usuario") as Usuario
                    var platoDiaPresenterImpl = PlatoDiaPresenterImpl()
                    platoDiaPresenterImpl.getPlatosSugerencia(usuario)
                    //}
                    postDelayed(this, time)
                }
            }
            postDelayed(runnable, time)
        }
        return START_STICKY
        //return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d(TAG, "destroy 😎")
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }

    public fun listPlatosSugerencia(platos: List<Plato>) {
        for (plato in platos) {
            Log.d(TAG, "idusuario : ${plato.idUsuarioCreador} / nombre: ${plato.nombre}")
        }
        if (platos.size > 0) {
            val platoSugerido = platos.random()
            Log.d(
                TAG,
                "El plato sugerido es: ${platoSugerido.idUsuarioCreador} / nombre: ${platoSugerido.nombre}"
            )
        }

    }
}