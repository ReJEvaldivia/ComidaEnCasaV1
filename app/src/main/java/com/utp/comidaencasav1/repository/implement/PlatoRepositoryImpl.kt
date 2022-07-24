package com.utp.comidaencasav1.repository.implement

import com.google.firebase.firestore.Query
import com.utp.comidaencasav1.helper.ConstanteHelper
import com.utp.comidaencasav1.model.Plato
import com.utp.comidaencasav1.presenter.interfaces.PlatoPresenter
import com.utp.comidaencasav1.repository.interfaces.PlatoRepository
import com.utp.comidaencasav1.repository.network.FirestoreService

class PlatoRepositoryImpl(var platoPresenter: PlatoPresenter) : PlatoRepository {
    private val firestoreService = FirestoreService<Plato>()
    private val platoRef = firestoreService.getCollectionRef(ConstanteHelper.COLLECTION_NAME_PLATO)

    override fun getPlatosFirebase(idUsuarioCreador: Int) {
        platoRef.whereEqualTo("idUsuarioCreador", idUsuarioCreador).orderBy("nombre")
            .get()
            .addOnSuccessListener { querySnapshot ->
                var platos = firestoreService.getListModel(querySnapshot, Plato::class.java)
                platoPresenter.showPlatos(platos)
            }
    }

    override fun setPlatoFirebase(plato: Plato) {
        platoRef.orderBy("idPlato", Query.Direction.DESCENDING).limit(1)
            .get()//Recupera el último idPlato registrado en la BD
            .addOnSuccessListener { querySnapshot ->
                var platos = firestoreService.getListModel(querySnapshot, Plato::class.java)
                plato.idPlato = if (platos.size > 0) platos[0].idPlato + 1 else 1

                val newPlatoRef = platoRef.document()
                plato.idDocumento = newPlatoRef.id//Asignar el idDocumento

                //platoRef.add(plato)
                newPlatoRef.set(plato)
                    .addOnSuccessListener {
                        platoPresenter.navigatePlatosFragment()
                    }
                //.addOnFailureListener { e -> Log.d("Firebase Message","Error writing document",e) }
            }
    }

    override fun updatePlatoFirebase(plato: Plato) {
        platoRef.document(plato.idDocumento)
            .update(
                mapOf(
                    "nombre" to plato.nombre,
                    "estadoVisibilidad" to plato.estadoVisibilidad
                )
            ).addOnSuccessListener {
                platoPresenter.navigatePlatosFragment()
            }
    }

    override fun deletePlatoFirebase(idDocumento: String) {
        platoRef.document(idDocumento)
            .delete().addOnSuccessListener {
                platoPresenter.navigatePlatosFragment()
            }
    }

    override fun getListaFirebase(idCuenta: Int) {
        platoRef.whereEqualTo("idCuenta", idCuenta).orderBy("nombre")
            .get()
            .addOnSuccessListener { querySnapshot ->
                var platos = firestoreService.getListModel(querySnapshot, Plato::class.java)
                platoPresenter.showPlatos(platos)
            }
    }
}