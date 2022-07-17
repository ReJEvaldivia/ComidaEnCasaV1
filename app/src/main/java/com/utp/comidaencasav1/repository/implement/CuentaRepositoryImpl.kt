package com.utp.comidaencasav1.repository.implement

import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import com.utp.comidaencasav1.helper.ConstanteHelper
import com.utp.comidaencasav1.model.Cuenta
import com.utp.comidaencasav1.presenter.interfaces.CuentaPresenter
import com.utp.comidaencasav1.repository.interfaces.CuentaRepository
import com.utp.comidaencasav1.repository.network.FirestoreService

class CuentaRepositoryImpl(var cuentaPresenter: CuentaPresenter) : CuentaRepository {
    private val firestoreService = FirestoreService<Cuenta>()
    private val cuentaRef =
        firestoreService.getCollectionRef(ConstanteHelper.COLLECTION_NAME_CUENTA)

    override fun getCuentaFirebase(cuenta: Cuenta) {
        cuentaRef.whereEqualTo("correo", cuenta.correo)
            .whereEqualTo("contrasena", cuenta.contrasena).limit(1)
            .get()
            .addOnSuccessListener { querySnapshot ->
                //Si existe registro con usuario
                if (querySnapshot.size() > 0) {
                    var cuentaRegistro: Cuenta = Cuenta()
                    for (documentSnapshot in querySnapshot) {
                        cuentaRegistro = documentSnapshot.toObject<Cuenta>()!!
                        break
                    }
                    cuentaPresenter.validateInicioSesion(cuentaRegistro)
                } else {
                    cuentaPresenter.validateInicioSesion(null)
                }
            }
    }

    override fun setCuentaFirebase(cuenta: Cuenta) {
        cuentaRef.orderBy("idCuenta", Query.Direction.DESCENDING).limit(1)
            .get()//Recupera el último idCuenta registrado en la BD
            .addOnSuccessListener { querySnapshot ->
                var cuentas = firestoreService.getListModel(querySnapshot, Cuenta::class.java)
                cuenta.idCuenta = if (cuentas.size > 0) cuentas[0].idCuenta + 1 else 1

                val newCuentaRef = cuentaRef.document()
                cuenta.idDocumento = newCuentaRef.id//Asignar el idDocumento

                newCuentaRef.set(cuenta)
                    .addOnSuccessListener {
                        cuentaPresenter.navigateLoginActivity()
                    }
            }
    }
/*
    override fun updateCuentaFirebase(cuenta: Cuenta) {
        cuentaRef.document(cuenta.idDocumento)
            .update(
                mapOf(
                    "familiaNombre" to cuenta.familiaNombre,
                    "correo" to cuenta.correo,
                    "contrasena" to cuenta.contrasena
                )
            ).addOnSuccessListener {
                cuentaPresenter.navigateNavCuentas()
            }

    }

    override fun deleteCuentaFirebase(idDocumento: String) {
       cuentaRef.document(idDocumento)
            .delete().addOnSuccessListener {
                cuentaPresenter.navigateNavCuentas()
            }

    }
 */

}