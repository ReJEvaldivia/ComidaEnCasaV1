package com.utp.comidaencasav1.view.fragment.insumos

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utp.comidaencasav1.R
import com.utp.comidaencasav1.adapter.fragment.insumos.InsumosAdapter
import com.utp.comidaencasav1.databinding.FragmentInsumosBinding
import com.utp.comidaencasav1.helper.ExtraHelper
import com.utp.comidaencasav1.helper.OperacionHelper
import com.utp.comidaencasav1.model.Cuenta
import com.utp.comidaencasav1.model.Insumo
import com.utp.comidaencasav1.model.Usuario
import com.utp.comidaencasav1.presenter.interfaces.InsumoPresenter
import com.utp.comidaencasav1.presenter.implement.InsumoPresenterImpl
import com.utp.comidaencasav1.presenter.implement.UsuarioPresenterImpl
import com.utp.comidaencasav1.presenter.interfaces.UsuarioPresenter
import com.utp.comidaencasav1.service.Service
import com.utp.comidaencasav1.view.activity.MainActivity
import com.utp.comidaencasav1.view.interfaces.InsumoView
import com.utp.comidaencasav1.view.interfaces.UsuarioView

class InsumosFragment : Fragment(), InsumoView {

    private var _binding: FragmentInsumosBinding? = null
    private val binding get() = _binding!!

    private var extraHelper: ExtraHelper? = null
    private var operacionHelper: OperacionHelper? = null
    private var insumoPresenter: InsumoPresenter? = null
    private var usuarioPresenter: UsuarioPresenter? = null
    private var rvInsumos: RecyclerView? = null
    private var usuario: Usuario? = null
    private var btnNuevoInsumo: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInsumosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initialConfig()

        getInsumos(usuario!!.idCuenta)

        btnNuevoInsumo!!.setOnClickListener {
            navigateInsumosAddUpdateFragment()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun showInsumos(insumos: List<Insumo>) {
        try {
            rvInsumos!!.adapter = InsumosAdapter(
                insumos,
                R.layout.card_insumos
            )//Llama al CardView y lo setea en el Adapter del ReciclerView
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initialConfig() {
        extraHelper = ExtraHelper()
        operacionHelper = OperacionHelper()
        usuario = extraHelper!!.getExtUsuario(requireActivity())

        //Instancia con el presentador
        insumoPresenter = InsumoPresenterImpl(this)

        //VIEW
        rvInsumos = binding.rcvInsumosInsumo//UI
        rvInsumos?.layoutManager = LinearLayoutManager(this.context)

        btnNuevoInsumo = binding.btnNuevoInsumo
    }

    private fun navigateInsumosAddUpdateFragment() {
        binding.root.findNavController().navigate(R.id.nav_insumosAddUpdateFragment)
    }

    override fun navigateInsumosFragmentOPlatosDetalleFragment() {
        TODO("Not yet implemented")
    }

    private fun getInsumos(idCuenta: Int) {
        insumoPresenter?.getInsumos(idCuenta)
    }
}