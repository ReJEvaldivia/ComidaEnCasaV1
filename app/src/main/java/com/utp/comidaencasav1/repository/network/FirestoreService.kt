package com.utp.comidaencasav1.repository.network

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class FirestoreService<T>() {

    //TODA LA LÓGICA DE CONEXIÓN
    private fun getInstance(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    fun getCollectionRef(collectionName: String): CollectionReference {
        return getInstance().collection(collectionName)
    }

    fun getArrayListModel(querySnapshot: QuerySnapshot, clazz: Class<T>): ArrayList<T> {
        return ArrayList(querySnapshot.toObjects(clazz))
    }

    fun getListModel(querySnapshot: QuerySnapshot, clazz: Class<T>): List<T> {
        return querySnapshot.toObjects(clazz)
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
        var platos: ArrayList<Plato>? = ArrayList()
            for (document in querySnapshot) {
                val plato = document.toObject<Plato>()
                plato.idDocumento = document.id
                platos?.add(plato)
            }
         platoPresenter.showPlatos(platos)
     }.addOnFailureListener { exception ->
         Log.d("Firebase Message", "Error writing document", exception)
     }*/

}