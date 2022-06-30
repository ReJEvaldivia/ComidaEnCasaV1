package com.utp.comidaencasav1.view.fragment.lista

import androidx.lifecycle.ViewModelProvider
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
import com.utp.comidaencasav1.model.Plato
import com.utp.comidaencasav1.presenter.interfaces.PlatoPresenter
import com.utp.comidaencasav1.presenter.implement.PlatoPresenterImpl
import com.utp.comidaencasav1.view.interfaces.PlatoView

class ListaFragment : Fragment(), PlatoView {

    private var _binding: FragmentListaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var platoPresenter: PlatoPresenter? = null
    private var rvLista: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val listaViewModel =
            ViewModelProvider(this).get(ListaViewModel::class.java)

        _binding = FragmentListaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        platoPresenter = PlatoPresenterImpl(this)

        //VIEW
        rvLista = binding.rcvPlatosLista//UI
        rvLista?.layoutManager = LinearLayoutManager(this.context)

        getPlatos(3)


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun showPlatos(platos: ArrayList<Plato>) {
        try {
            rvLista!!.adapter = ListaAdapter(
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

    private fun getPlatos(idUsuarioCreador: Int) {
        platoPresenter?.getPlatos(idUsuarioCreador)
    }
}