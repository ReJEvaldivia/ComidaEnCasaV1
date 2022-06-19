package com.utp.comidaencasav1.view.fragment.platos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utp.comidaencasav1.R
import com.utp.comidaencasav1.adapter.fragment.platos.PlatosAdapter
import com.utp.comidaencasav1.databinding.FragmentPlatosBinding
import com.utp.comidaencasav1.model.Plato
import com.utp.comidaencasav1.presenter.interfaces.PlatoPresenter
import com.utp.comidaencasav1.presenter.implement.PlatoPresenterImpl
import com.utp.comidaencasav1.view.interfaces.PlatoView

class PlatosFragment : Fragment(), PlatoView {

    private var _binding: FragmentPlatosBinding? = null
    private val binding get() = _binding!!

    private var platoPresenter: PlatoPresenter? = null
    private var rvPlatos: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val platosViewModel =
            ViewModelProvider(this).get(PlatosViewModel::class.java)

        _binding = FragmentPlatosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        platoPresenter = PlatoPresenterImpl(this)

        //VIEW
        rvPlatos = binding.rcvPlatosPlato//UI
        rvPlatos?.layoutManager = LinearLayoutManager(this.context)

        getPlatos(3)

        _binding!!.btnNuevoPlato.setOnClickListener {
            root.findNavController().navigate(R.id.nav_platosAddUpdateFragment)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun showPlatos(platos: ArrayList<Plato>?) {
        try {
            rvPlatos!!.adapter = PlatosAdapter(
                platos,
                R.layout.card_platos
            )//Llama al CardView y lo setea en el Adapter del ReciclerView
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getPlatos(idUsuarioCreador: Int) {
        platoPresenter?.getPlatos(idUsuarioCreador)
    }

}