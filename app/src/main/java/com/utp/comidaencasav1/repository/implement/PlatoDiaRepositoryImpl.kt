package com.utp.comidaencasav1.repository.implement

import com.utp.comidaencasav1.helper.ConstanteHelper
import com.utp.comidaencasav1.model.Plato
import com.utp.comidaencasav1.model.PlatoDia
import com.utp.comidaencasav1.model.Usuario
import com.utp.comidaencasav1.presenter.interfaces.PlatoDiaPresenter
import com.utp.comidaencasav1.repository.interfaces.PlatoDiaRepository
import com.utp.comidaencasav1.repository.network.FirestoreService

class PlatoDiaRepositoryImpl(var platoDiaPresenter: PlatoDiaPresenter) : PlatoDiaRepository {
    private val firestoreService = FirestoreService<PlatoDia>()
    private val platofirestoreService = FirestoreService<Plato>()
    private val usuariofirestoreService = FirestoreService<Usuario>()
    private val platoDiaRef =
        firestoreService.getCollectionRef(ConstanteHelper.COLLECTION_NAME_PLATODIA)
    private val platoRef =
        platofirestoreService.getCollectionRef(ConstanteHelper.COLLECTION_NAME_PLATO)
    private val usuarioRef =
        usuariofirestoreService.getCollectionRef(ConstanteHelper.COLLECTION_NAME_USUARIO)

    override fun getPlatosSugerenciaFirebase(usuario: Usuario) {
        //Lista de usuarios
        var listUser = arrayListOf<Int>()
        //listUser.addAll(listOf(1, 5))
        //Recuperar todos los usuarios para almacenarlos en una lista
        usuarioRef.whereEqualTo("idCuenta", usuario.idCuenta).limit(8)
            .get()
            .addOnSuccessListener { querySnapshot ->
                var usuarios =
                    usuariofirestoreService.getListModel(querySnapshot, Usuario::class.java)
                for (usuario2 in usuarios) {
                    listUser.add(usuario2.idUsuario)
                }
                //Recuperar todos los platos de los usuarios que se encuentren en la lista
                platoRef.whereIn("idUsuarioCreador", listUser)
                    .whereEqualTo("estadoVisibilidad", true)//Si participa el plato
                    .whereEqualTo(
                        "estadoCategoriaSemanal",
                        true
                    )//Si ya pasaron los días para que vuelva a ser considerado en la sugerencia
                    .get()
                    .addOnSuccessListener { querySnapshot ->
                        var platos =
                            platofirestoreService.getListModel(querySnapshot, Plato::class.java)
                        platoDiaPresenter!!.listPlatosSugerencia(platos)
                    }
            }
    }
}