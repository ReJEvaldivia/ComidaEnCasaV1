package com.utp.comidaencasav1.model.repositories

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.utp.comidaencasav1.model.models.Plato
import com.utp.comidaencasav1.model.models.Usuario
import com.utp.comidaencasav1.presenter.PlatoPresenter

class PlatoRepositoryImpl(var platoPresenter: PlatoPresenter) : PlatoRepository {
    //TODA LA LÓGICA DE CONEXIÓN
    override fun getPlatosFirebase(idUsuarioCreador: Int) {
        //CONTROLLER
        var platos: ArrayList<Plato>? = ArrayList<Plato>()
        var plato1: Plato = Plato()
        plato1.idPlato = 1
        plato1.nombre = "Arroz con pollo"
        var plato2: Plato = Plato()
        plato2.idPlato = 2
        plato2.nombre = "Tallarines verdes"
        platos?.add(plato1)
        platos?.add(plato2)

        //Mostrar los platos
        //platoPresenter.showPlatos(platos)

        //Traer los platos de Firebase
        getShowPlatos(idUsuarioCreador)

    }

    fun getShowPlatos(idUsuarioCreador: Int) {
        //Log.d("Datos TEST 1", "HOLAS")
        val db = FirebaseFirestore.getInstance()
        val platoRef = db.collection("Plato")

        //Recupera con filtros
        platoRef.whereEqualTo("idUsuarioCreador", idUsuarioCreador).orderBy("nombre")
            .get()
            .addOnSuccessListener { querySnapshot ->
                var platos: ArrayList<Plato>? = ArrayList()
                for (document in querySnapshot) {
                    val plato = document.toObject<Plato>()
                    plato.idDocumento = document.id
                    platos?.add(plato)
                }
                platoPresenter.showPlatos(platos)
            }

        /*
        //Recupera 1 documento
        platoRef.document("FjvPTqZZw54xYCzBvdgk").get().addOnSuccessListener { documentSnapshot ->
            val plato = documentSnapshot.toObject<Plato>()!!
            plato.idDocumento = documentSnapshot.id
            var platos: ArrayList<Plato>? = ArrayList()
            platos?.add(plato)
            platoPresenter.showPlatos(platos)
        }

        //Recupera todos los documentos
        platoRef.get().addOnSuccessListener { querySnapshot ->
            val platos = ArrayList(querySnapshot.toObjects<Plato>())
            platoPresenter.showPlatos(platos)
        }.addOnFailureListener { exception ->
            Log.d("Firebase Message", "Error writing document", exception)
        }*/


    }


}