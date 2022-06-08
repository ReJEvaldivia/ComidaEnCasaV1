package com.utp.comidaencasav1.model.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import com.utp.comidaencasav1.model.models.Plato
import com.utp.comidaencasav1.presenter.PlatoPresenter

class PlatoRepositoryImpl(var platoPresenter: PlatoPresenter) : PlatoRepository {
    //TODA LA LÓGICA DE CONEXIÓN
    override fun getPlatosFirebase() {
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
        getShowPlatos()

    }

    fun getShowPlatos() {
        //Log.d("Datos TEST 1", "HOLAS")
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("Plato")

        //Recupera con filtros
        docRef.whereEqualTo("idCuenta", 1).whereEqualTo("idUsuarioCreador", 3).orderBy("nombre")
            .get()
            .addOnSuccessListener { documents ->
                var platos: ArrayList<Plato>? = ArrayList()
                for (document in documents) {
                    val plato = document.toObject<Plato>()!!
                    plato.idDocumento = document.id
                    platos?.add(plato)
                }
                platoPresenter.showPlatos(platos)
            }

        /*
        //Recupera 1 documento
        docRef.document("FjvPTqZZw54xYCzBvdgk").get().addOnSuccessListener { document ->
            val plato = document.toObject<Plato>()!!
            plato.idDocumento = document.id
            var platos: ArrayList<Plato>? = ArrayList()
            platos?.add(plato)
            platoPresenter.showPlatos(platos)
        }

        //Recupera todos los documentos
        docRef.get().addOnSuccessListener { documents ->
            val platos = ArrayList(documents.toObjects<Plato>())
            platoPresenter.showPlatos(platos)
        }.addOnFailureListener { exception ->
            Log.d("Firebase Message", "Error writing document", exception)
        }*/


    }
}