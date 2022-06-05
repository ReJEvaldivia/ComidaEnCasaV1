package com.utp.comidaencasav1.model.repositories

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
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
        getShowPlatos(platos)

    }

    fun getShowPlatos(platos: ArrayList<Plato>?) {
        var name: String = ""
        var idPlatoCount = 3

        Log.d("Datos TEST 1", "HJOLAS")
        val db = FirebaseFirestore.getInstance()
        db.collection("Plato")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    name = document.getString("Nombre")!!
                    Log.d("Datos TEST 2", "$name")


                    var plato3: Plato = Plato()
                    plato3.idPlato = idPlatoCount
                    plato3.nombre = "Firebase: $name"
                    platos?.add(plato3)
                    idPlatoCount += 1

                }
                platoPresenter.showPlatos(platos)
            }
            .addOnFailureListener { exception -> {} }

        Log.d("Datos TEST 3", "$name")

    }
}