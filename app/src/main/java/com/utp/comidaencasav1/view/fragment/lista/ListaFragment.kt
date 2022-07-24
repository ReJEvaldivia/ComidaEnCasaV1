package com.utp.comidaencasav1.view.fragment.lista

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utp.comidaencasav1.R
import com.utp.comidaencasav1.adapter.fragment.lista.ListaAdapter
import com.utp.comidaencasav1.databinding.FragmentListaBinding
import com.utp.comidaencasav1.helper.ExtraHelper
import com.utp.comidaencasav1.model.Plato
import com.utp.comidaencasav1.model.Usuario
import com.utp.comidaencasav1.presenter.implement.PlatoPresenterImpl
import com.utp.comidaencasav1.presenter.interfaces.PlatoPresenter
import com.utp.comidaencasav1.view.interfaces.PlatoView

class ListaFragment : Fragment(), PlatoView {

    private var _binding: FragmentListaBinding? = null
    private val binding get() = _binding!!

    private var extraHelper: ExtraHelper? = null
    private var platoPresenter: PlatoPresenter? = null
    private var rvListas: RecyclerView? = null
    private var usuario: Usuario? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initialConfig()

        getLista(usuario!!.idCuenta)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun showPlatos(platos: List<Plato>) {
        try {
            rvListas!!.adapter = ListaAdapter(
                platos,
                R.layout.card_lista
            )//Llama al CardView y lo setea en el Adapter del ReciclerView
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun navigatePlatosFragment() {
        TODO("Not yet implemented")
    }

    private fun initialConfig() {
        extraHelper = ExtraHelper()
        usuario = extraHelper!!.getExtUsuario(requireActivity())
        //Instancia con el presentador
        platoPresenter = PlatoPresenterImpl(this)

        //VIEW
        rvListas = binding.rcvPlatosLista//UI
        rvListas?.layoutManager = LinearLayoutManager(this.context)
    }

    private fun getLista(idCuenta: Int) {
        platoPresenter?.getLista(idCuenta)
    }


}