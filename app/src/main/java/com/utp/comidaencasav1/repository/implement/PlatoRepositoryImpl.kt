package com.utp.comidaencasav1.repository.implement

import android.util.Log
import androidx.navigation.findNavController
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObjects
import com.utp.comidaencasav1.R
import com.utp.comidaencasav1.helper.ExtraHelper
import com.utp.comidaencasav1.helper.OperacionesHelper
import com.utp.comidaencasav1.model.Plato
import com.utp.comidaencasav1.presenter.interfaces.PlatoPresenter
import com.utp.comidaencasav1.repository.interfaces.PlatoRepository
import com.utp.comidaencasav1.repository.network.FirestoreService

const val PLATO_COLLECTION_NAME = "Plato"
const val CUENTA_COLLECTION_NAME = "Cuenta"
const val USUARIO_COLLECTION_NAME = "Usuario"

class PlatoRepositoryImpl(var platoPresenter: PlatoPresenter) : PlatoRepository {
    val firestoreService = FirestoreService<Plato>()
    val platoRef = firestoreService.getCollectionRef(PLATO_COLLECTION_NAME)
    private var operacionesHelper: OperacionesHelper = OperacionesHelper()

    override fun getPlatosFirebase(idUsuarioCreador: Int) {
        //Recupera con filtros
        platoRef.whereEqualTo("idUsuarioCreador", idUsuarioCreador).orderBy("nombre")
            .get()
            .addOnSuccessListener { querySnapshot ->
                var platos = firestoreService.getArrayListModel(querySnapshot, Plato::class.java)
                platoPresenter.showPlatos(platos)
            }
    }

    override fun setPlatoFirebase(plato: Plato) {
        //INSERT
        platoRef.orderBy("idPlato", Query.Direction.DESCENDING).limit(1)
            .get()//Recupera el último idPlato registrado en la BD
            .addOnSuccessListener { querySnapshot ->
                var platos = firestoreService.getArrayListModel(querySnapshot, Plato::class.java)
                plato.idPlato = if (platos.size > 0) platos[0].idPlato + 1 else 1
                val newPlatoRef = platoRef.document()
                plato.idDocumento = newPlatoRef.id

                //platoRef.add(plato)
                newPlatoRef.set(plato)
                    .addOnSuccessListener {
                    platoPresenter.navigateNavPlatos()
                    }
                //.addOnFailureListener { e -> Log.d("Firebase Message","Error writing document",e) }
            }
    }

    override fun updatePlatoFirebase(plato: Plato) {
        //UPDATE
        platoRef.document(plato.idDocumento)
            .update(
                mapOf(
                    "nombre" to plato.nombre,
                    "estadoVisibilidad" to plato.estadoVisibilidad
                )
            ).addOnSuccessListener {
                platoPresenter.navigateNavPlatos()
            }
    }

    override fun deletePlatoFirebase(idDocumento: String) {
        //DELETE
        platoRef.document(idDocumento)
            .delete().addOnSuccessListener {
                platoPresenter.navigateNavPlatos()
            }
    }
}