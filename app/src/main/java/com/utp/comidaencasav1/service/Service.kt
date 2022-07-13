package com.utp.comidaencasav1.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log
import com.utp.comidaencasav1.model.PlatoDia
import com.utp.comidaencasav1.model.Usuario
import com.utp.comidaencasav1.presenter.implement.PlatoDiaPresenterImpl
import java.text.SimpleDateFormat
import java.util.*

class Service() : Service() {
    private val TAG = "ComidaEnCasaV1 service"
    val handler = Handler()
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "start")
        var contador = 0
        var time: Long = 60 * 1000//Equivale a 60 segundos
        val sdf = SimpleDateFormat("hh")
        handler.apply {
            val runnable = object : Runnable {
                override fun run() {
                    contador++
                    Log.d(TAG, "Valor contador: $contador")
                    var hour = sdf.format(Date()).toInt()
                    if (hour == 6) {//Cuando sea las 06 horas
                        //Log.d(TAG, "Hora actual: $hour")
                        val bundle = intent!!.extras
                        val usuario = bundle!!.getSerializable("ext_usuario") as Usuario
                        var platoDiaPresenterImpl = PlatoDiaPresenterImpl()
                        platoDiaPresenterImpl.setPlatoSugerencia(usuario.idCuenta)
                    }
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

    fun successSetPlatoDia(success: Boolean, platoDia: PlatoDia? = null) {
        //Log.d(TAG,"Info de plato del día: successSetPlatoDia=$success / idPlato=${platoDia?.idPlato} / fecha=${platoDia?.fecha}")
    }
}