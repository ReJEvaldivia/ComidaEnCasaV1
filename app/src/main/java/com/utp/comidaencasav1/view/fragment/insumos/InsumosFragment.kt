package com.utp.comidaencasav1.view.fragment.insumos

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utp.comidaencasav1.R
import com.utp.comidaencasav1.adapter.fragment.insumos.InsumosAdapter
import com.utp.comidaencasav1.databinding.FragmentInsumosBinding
import com.utp.comidaencasav1.model.Plato
import com.utp.comidaencasav1.presenter.interfaces.PlatoPresenter
import com.utp.comidaencasav1.presenter.implement.PlatoPresenterImpl
import com.utp.comidaencasav1.view.interfaces.PlatoView

class InsumosFragment : Fragment() , PlatoView {

    private var _binding: FragmentInsumosBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var platoPresenter: PlatoPresenter? = null
    private var rvInsumos: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val insumosViewModel =
            ViewModelProvider(this).get(InsumosViewModel::class.java)

        _binding = FragmentInsumosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        platoPresenter = PlatoPresenterImpl(this)

        //VIEW
        rvInsumos = binding.rcvInsumosInsumo//UI
        rvInsumos?.layoutManager = LinearLayoutManager(this.context)

        getPlatos(3)

        _binding!!.btnNuevoInsumo.setOnClickListener {
            root.findNavController().navigate(R.id.nav_insumosAddUpdateFragment)
        }

        //val nav = Navigation.createNavigateOnClickListener(R.id.nav_platosAddUpdateFragment)

        /*_binding!!.btnNuevoInsumo.setOnClickListener({
            nav.onClick(it)
        })

        _binding!!.btnGuardarInsumo.setOnClickListener({
            nav.onClick(it)
        })*/

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun showPlatos(platos: ArrayList<Plato>) {
        try {
            rvInsumos!!.adapter = InsumosAdapter(
                platos,
                R.layout.card_insumos
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