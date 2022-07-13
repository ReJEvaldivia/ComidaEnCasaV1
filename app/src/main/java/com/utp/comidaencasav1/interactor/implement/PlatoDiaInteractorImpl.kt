package com.utp.comidaencasav1.interactor.implement

import com.utp.comidaencasav1.interactor.interfaces.PlatoDiaInteractor
import com.utp.comidaencasav1.model.Plato
import com.utp.comidaencasav1.model.PlatoDia
import com.utp.comidaencasav1.presenter.interfaces.PlatoDiaPresenter
import com.utp.comidaencasav1.repository.implement.PlatoDiaRepositoryImpl
import com.utp.comidaencasav1.repository.interfaces.PlatoDiaRepository
import java.util.*

class PlatoDiaInteractorImpl(var platoDiaPresenter: PlatoDiaPresenter) : PlatoDiaInteractor {
    private var platoDiaRepository: PlatoDiaRepository = PlatoDiaRepositoryImpl(platoDiaPresenter)
    private val TAG = "ComidaEnCasaV1 service"

    override fun getPlatosSugerenciaFirebase(idCuenta: Int) {
        platoDiaRepository.getPlatosSugerenciaFirebase(idCuenta)
    }

    override fun setPlatoSugerenciaFirebase(platos: List<Plato>) {
        /*for (plato in platos) {
            Log.d(TAG, "Info de plato: idCuenta=${plato.idCuenta} / idusuario=${plato.idUsuarioCreador} / nombre=${plato.nombre}")
        }*/
        if (platos.isNotEmpty()) {
            //Obtener el plato sugerido (random) de la lista de platos de la cuenta
            val platoSugerido = platos.random()
            val platoDia: PlatoDia = PlatoDia()
            platoDia.idCuenta = platoSugerido.idCuenta
            platoDia.idPlato = platoSugerido.idPlato
            platoDia.idCategoria = platoSugerido.idCategoria
            platoDia.fecha = Date()
            platoDiaRepository.setPlatoDiaFirebase(platoDia)
        }
    }

    override fun existsPlatosSugerenciaFirebase(idCuenta: Int) {
        platoDiaRepository.existsPlatosSugerenciaFirebase(idCuenta)
    }


}