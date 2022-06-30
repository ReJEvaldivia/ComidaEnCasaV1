package com.utp.comidaencasav1.repository.implement

import android.content.Intent
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObjects
import com.utp.comidaencasav1.helper.ConstanteHelper
import com.utp.comidaencasav1.model.Usuario
import com.utp.comidaencasav1.presenter.interfaces.UsuarioPresenter
import com.utp.comidaencasav1.repository.interfaces.UsuarioRepository
import com.utp.comidaencasav1.repository.network.FirestoreService
import com.utp.comidaencasav1.view.activity.PerfilActivity

class UsuarioRepositoryImpl(var usuarioPresenter: UsuarioPresenter) : UsuarioRepository {
    private val firestoreService = FirestoreService<Usuario>()
    private val usuarioRef =
        firestoreService.getCollectionRef(ConstanteHelper.COLLECTION_NAME_USUARIO)

    override fun getUsuarioDefaultFirebase() {
        usuarioRef.whereEqualTo("idCuenta", 1).orderBy("idRol")
            .orderBy("idUsuario", Query.Direction.ASCENDING).limit(1)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val usuarios = querySnapshot.toObjects<Usuario>()
                var usuario = usuarios.first()
                usuarioPresenter.showUsuarioDefault(usuario)
            }
    }

    override fun getUsuariosFirebase(idCuenta: Int) {
        usuarioRef.whereEqualTo("idCuenta", idCuenta).orderBy("idRol").limit(8)
            .get()
            .addOnSuccessListener { querySnapshot ->
                var usuarios =
                    firestoreService.getListModel(querySnapshot, Usuario::class.java)
                usuarioPresenter.showPerfiles(usuarios)
            }
    }

    override fun setUsuarioFirebase(usuario: Usuario) {
        usuarioRef.orderBy("idUsuario", Query.Direction.DESCENDING)
            .limit(1).get()
            .addOnSuccessListener { querySnapshot ->
                //var usuarios = firestoreService.getArrayListModel(querySnapshot, Usuario::class.java)
                var usuarios =
                    firestoreService.getListModel(querySnapshot, Usuario::class.java)
                usuario.idUsuario = if (usuarios.size > 0) usuarios[0].idUsuario + 1 else 1

                val newUsuarioRef = usuarioRef.document()
                usuario.idDocumento = newUsuarioRef.id//Asignar el idDocumento

                usuarioRef.whereEqualTo("idCuenta", usuario.idCuenta).limit(1)
                    .get()
                    .addOnSuccessListener { querySnapshot ->
                        //Si existe por lo menos un perfil en la cuenta
                        if (querySnapshot.size() > 0) {
                            //Participante
                            usuario.idRol = 2
                        } else {
                            //Cocinero
                            usuario.idRol = 1
                        }

                        newUsuarioRef.set(usuario)
                            .addOnSuccessListener {
                                usuarioPresenter.navigatePerfilActivity()
                            }
                    }
            }
    }

    /* override fun updateUsuarioFirebase(usuario: Usuario) {
         usuarioRef.document(usuario.idDocumento)
             .update(
                 mapOf(
                     "nombre" to usuario.nombre
                 )
             ).addOnSuccessListener {
                 usuarioPresenter.navigateNavUsuarios()
             }
    }*/

    /*  override fun deleteUsuarioFirebase(idDocumento: String) {
          usuarioRef.document(idDocumento)
              .delete().addOnSuccessListener {
                  usuarioPresenter.navigateNavUsuarios()
              }
      }*/


}