package com.utp.comidaencasav1.repository.implement

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

    override fun getPlatosFirebase(idUsuarioCreador: Int) {
        //Recupera con filtros
        platoRef.whereEqualTo("idUsuarioCreador", idUsuarioCreador).orderBy("nombre")
            .get()
            .addOnSuccessListener { querySnapshot ->
                var platos = firestoreService.getArrayListModel(querySnapshot, Plato::class.java)
                platoPresenter.showPlatos(platos)
            }
    }
}