package com.utp.comidaencasav1.repository.implement

import com.google.firebase.firestore.Query
import com.utp.comidaencasav1.helper.ConstanteHelper
import com.utp.comidaencasav1.model.Insumo
import com.utp.comidaencasav1.presenter.interfaces.InsumoPresenter
import com.utp.comidaencasav1.repository.interfaces.InsumoRepository
import com.utp.comidaencasav1.repository.network.FirestoreService

class InsumoRepositoryImpl(var insumoPresenter: InsumoPresenter) : InsumoRepository {
    private val firestoreService = FirestoreService<Insumo>()
    private val insumoRef = firestoreService.getCollectionRef(ConstanteHelper.COLLECTION_NAME_INSUMO)

    override fun getInsumosFirebase(idCuenta: Int) {
        insumoRef.whereEqualTo("idCuenta", idCuenta).orderBy("nombre")
            .get()
            .addOnSuccessListener { querySnapshot ->
                var insumos = firestoreService.getArrayListModel(querySnapshot, Insumo::class.java)
                insumoPresenter.showInsumos(insumos)
            }
    }

    override fun setInsumoFirebase(insumo: Insumo) {
        insumoRef.orderBy("idInsumo", Query.Direction.DESCENDING).limit(1)
            .get()//Recupera el último idInsumo registrado en la BD
            .addOnSuccessListener { querySnapshot ->
                var insumos = firestoreService.getArrayListModel(querySnapshot, Insumo::class.java)
                insumo.idInsumo = if (insumos.size > 0) insumos[0].idInsumo + 1 else 1

                val newInsumoRef = insumoRef.document()
                insumo.idDocumento = newInsumoRef.id//Asignar el idDocumento

                //insumoRef.add(insumo)
                newInsumoRef.set(insumo)
                    .addOnSuccessListener {
                        insumoPresenter.navigateInsumosFragment()
                    }
                //.addOnFailureListener { e -> Log.d("Firebase Message","Error writing document",e) }
            }
    }

    override fun updateInsumoFirebase(insumo: Insumo) {
        insumoRef.document(insumo.idDocumento)
            .update(
                mapOf(
                    "nombre" to insumo.nombre,
                    "cantidad" to insumo.cantidad,
                    "unidad" to insumo.unidad
                )
            ).addOnSuccessListener {
                insumoPresenter.navigateInsumosFragment()
            }
    }

    override fun deleteInsumoFirebase(idDocumento: String) {
        insumoRef.document(idDocumento)
            .delete().addOnSuccessListener {
                insumoPresenter.navigateInsumosFragment()
            }
    }
}