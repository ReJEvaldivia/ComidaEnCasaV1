package com.utp.comidaencasav1.repository.implement

import android.os.Build
import androidx.annotation.RequiresApi
import com.utp.comidaencasav1.helper.ConstanteHelper
import com.utp.comidaencasav1.model.Plato
import com.utp.comidaencasav1.model.PlatoDia
import com.utp.comidaencasav1.model.Usuario
import com.utp.comidaencasav1.presenter.interfaces.PlatoDiaPresenter
import com.utp.comidaencasav1.repository.interfaces.PlatoDiaRepository
import com.utp.comidaencasav1.repository.network.FirestoreService
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PlatoDiaRepositoryImpl(var platoDiaPresenter: PlatoDiaPresenter) : PlatoDiaRepository {
    private val firestoreService = FirestoreService<PlatoDia>()
    private val platofirestoreService = FirestoreService<Plato>()
    private val usuariofirestoreService = FirestoreService<Usuario>()
    private val platoDiaRef =
        firestoreService.getCollectionRef(ConstanteHelper.COLLECTION_NAME_PLATODIA)
    private val platoRef =
        platofirestoreService.getCollectionRef(ConstanteHelper.COLLECTION_NAME_PLATO)
    private val usuarioRef =
        usuariofirestoreService.getCollectionRef(ConstanteHelper.COLLECTION_NAME_USUARIO)

    override fun getPlatosSugerenciaFirebase(idCuenta: Int) {
        //Recuperar todos los platos de la cuenta
        platoRef.whereEqualTo("idCuenta", idCuenta)
            .whereEqualTo("estadoVisibilidad", true)//Si participa el plato
            .whereEqualTo(
                "estadoCategoriaSemanal",
                true
            )//Si ya pasaron los días para que vuelva a ser considerado en la sugerencia
            .get()
            .addOnSuccessListener { querySnapshot ->
                var platos =
                    platofirestoreService.getListModel(querySnapshot, Plato::class.java)
                platoDiaPresenter!!.listPlatosSugerencia(platos)
            }
    }

    override fun setPlatoDiaFirebase(platoDia: PlatoDia) {
        val newPlatoDiaRef = platoDiaRef.document()
        platoDia.idDocumento = newPlatoDiaRef.id//Asignar el idDocumento

        newPlatoDiaRef.set(platoDia)
            .addOnSuccessListener {
                platoDiaPresenter.successSetPlatoDia(true, platoDia)
            }.addOnFailureListener {
                platoDiaPresenter.successSetPlatoDia(false, platoDia)
            }
    }

    /**
     * Validar si existe la sugerencia del plato en el día
     * */
    @RequiresApi(Build.VERSION_CODES.O)
    override fun existsPlatosSugerenciaFirebase(idCuenta: Int) {
        val currentDate1 = LocalDateTime.now()//Fecha actual
        val currentDate2 = currentDate1.plusDays(1)//Fecha actual +1 día
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val dateFormat1 = currentDate1.format(formatter)
        val dateFormat2 = currentDate2.format(formatter)
        val date1 = java.sql.Date.valueOf(dateFormat1)
        val date2 = java.sql.Date.valueOf(dateFormat2)

        platoDiaRef.whereEqualTo("idCuenta", idCuenta)
            .whereGreaterThanOrEqualTo("fecha", date1)
            .whereLessThan("fecha", date2)
            .get()
            .addOnSuccessListener { querySnapshot ->
                //var platoDias = firestoreService.getListModel(querySnapshot, PlatoDia::class.java)
                if (querySnapshot.size() > 0)
                    platoDiaPresenter!!.existsPlatosSugerenciaFirebase(true)
                else
                    platoDiaPresenter!!.existsPlatosSugerenciaFirebase(false)
            }
    }
}