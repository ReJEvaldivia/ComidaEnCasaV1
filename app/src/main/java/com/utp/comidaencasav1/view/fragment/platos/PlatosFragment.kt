package com.utp.comidaencasav1.view.fragment.platos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utp.comidaencasav1.R
import com.utp.comidaencasav1.adapter.fragment.platos.PlatosAdapter
import com.utp.comidaencasav1.databinding.FragmentPlatosBinding
import com.utp.comidaencasav1.helper.ExtraHelper
import com.utp.comidaencasav1.helper.OperacionHelper
import com.utp.comidaencasav1.model.Plato
import com.utp.comidaencasav1.model.Usuario
import com.utp.comidaencasav1.presenter.implement.PlatoPresenterImpl
import com.utp.comidaencasav1.presenter.implement.UsuarioPresenterImpl
import com.utp.comidaencasav1.presenter.interfaces.PlatoPresenter
import com.utp.comidaencasav1.presenter.interfaces.UsuarioPresenter
import com.utp.comidaencasav1.service.Service
import com.utp.comidaencasav1.view.activity.MainActivity
import com.utp.comidaencasav1.view.interfaces.PlatoView
import com.utp.comidaencasav1.view.interfaces.UsuarioView


class PlatosFragment : Fragment(), PlatoView, UsuarioView {

    private var _binding: FragmentPlatosBinding? = null
    private val binding get() = _binding!!

    private var extraHelper: ExtraHelper? = null
    private var operacionHelper: OperacionHelper? = null
    private var platoPresenter: PlatoPresenter? = null
    private var usuarioPresenter: UsuarioPresenter? = null
    private var rvPlatos: RecyclerView? = null
    private var svBuscar: SearchView? = null
    private var usuario: Usuario? = null
    private var btnNuevoPlato: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlatosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initialConfig()

        getPlatos(usuario!!.idUsuario)

        btnNuevoPlato!!.setOnClickListener {
            navigatePlatosAddUpdateFragment()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun showPlatos(platos: ArrayList<Plato>) {
        try {
            rvPlatos!!.adapter = PlatosAdapter(
                platos,
                R.layout.card_platos
            )//Llama al CardView y lo setea en el Adapter del ReciclerView
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initialConfig() {
        extraHelper = ExtraHelper()
        operacionHelper = OperacionHelper()
        usuario = getUsuarioInitialConfig()
        //Instancia con el presentador
        platoPresenter = PlatoPresenterImpl(this)

        //VIEW
        rvPlatos = binding.rcvPlatosPlato//UI
        rvPlatos?.layoutManager = LinearLayoutManager(this.context)
        svBuscar = binding.svBusquedaPlatos

        btnNuevoPlato = binding.btnNuevoPlato
        //svBuscar!!.setOnQueryTextListener(this)
    }

    private fun navigatePlatosAddUpdateFragment() {
        binding.root.findNavController().navigate(R.id.nav_platosAddUpdateFragment)
    }

    override fun navigatePlatosFragment() {
        TODO("Not yet implemented")
    }

    private fun getPlatos(idUsuarioCreador: Int) {
        platoPresenter?.getPlatos(idUsuarioCreador)
    }

    override fun showUsuarioDefault(usuario: Usuario) {
        val it = extraHelper?.setExtUsuario(context, usuario, MainActivity::class.java)
        context?.startActivity(it)
    }

    override fun navigatePerfilActivity() {
        TODO("Not yet implemented")
    }

    override fun showPerfiles(usuarios: List<Usuario>) {
        TODO("Not yet implemented")
    }

    override fun getUsuarioInitialConfig(): Usuario {
        //Recuperar el extra
        val bundleExt = operacionHelper!!.getBundle(requireActivity())
        var usuario2 = Usuario()
        if (bundleExt != null) {
            //Recuperar el usuario
            usuario2 = extraHelper!!.getExtUsuario(requireActivity())
            callService(usuario2)
        } else {
            //Esto se hace para iniciar desde el Main con el usuario por defecto en la BD Firebase
            usuarioPresenter = UsuarioPresenterImpl(this)
            getUsuarioDefaultInitialConfig()
        }
        return usuario2
    }

    override fun getUsuarioDefaultInitialConfig() {
        usuarioPresenter?.getUsuarioDefault()
    }

    /**
     * Llama al servicio de sugerencia aleatoria
     * */
    private fun callService(usuario2: Usuario) {
        val intent = extraHelper?.setExtUsuario(context, usuario2, Service::class.java)
        context?.stopService(intent)
        context?.startService(intent)
    }

}