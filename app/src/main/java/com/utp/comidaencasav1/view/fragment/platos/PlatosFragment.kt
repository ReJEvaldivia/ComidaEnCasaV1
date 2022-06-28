package com.utp.comidaencasav1.view.fragment.platos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObjects
import com.utp.comidaencasav1.R
import com.utp.comidaencasav1.adapter.fragment.platos.PlatosAdapter
import com.utp.comidaencasav1.databinding.FragmentPlatosBinding
import com.utp.comidaencasav1.helper.ArgumentoHelper
import com.utp.comidaencasav1.helper.ExtraHelper
import com.utp.comidaencasav1.helper.OperacionHelper
import com.utp.comidaencasav1.model.Plato
import com.utp.comidaencasav1.model.Usuario
import com.utp.comidaencasav1.presenter.implement.PlatoPresenterImpl
import com.utp.comidaencasav1.presenter.implement.UsuarioPresenterImpl
import com.utp.comidaencasav1.presenter.interfaces.PlatoPresenter
import com.utp.comidaencasav1.presenter.interfaces.UsuarioPresenter
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlatosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initialConfig()

        getPlatos(usuario!!.idUsuario)

        _binding!!.btnNuevoPlato.setOnClickListener {
            root.findNavController().navigate(R.id.nav_platosAddUpdateFragment)
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
        usuario = getUsuario()
        //Instancia con el presentador
        platoPresenter = PlatoPresenterImpl(this)

        //VIEW
        rvPlatos = binding.rcvPlatosPlato//UI
        rvPlatos?.layoutManager = LinearLayoutManager(this.context)
        svBuscar = binding.svBusquedaPlatos

        //svBuscar!!.setOnQueryTextListener(this)
    }

    override fun navigateNavPlatos() {
        TODO("Not yet implemented")
    }

    override fun getPlatos(idUsuarioCreador: Int) {
        platoPresenter?.getPlatos(idUsuarioCreador)
    }

    override fun setPlato(plato: Plato) {
        TODO("Not yet implemented")
    }

    override fun updatePlato(plato: Plato) {
        TODO("Not yet implemented")
    }

    override fun deletePlato(idDocumento: String) {
        TODO("Not yet implemented")
    }

    fun getUsuario(): Usuario {
        //Recuperar el usuario
        //val usuario = requireActivity().intent.extras!!.get("ext_usuario") as Usuario
        //Recuperar el extra
        val bundleExt =  operacionHelper!!.getBundle(requireActivity())
        var usuario = Usuario()
        if (bundleExt != null) {
            //Recuperar el usuario
            usuario = extraHelper!!.getExtUsuario(requireActivity())
        } else {
            //Esto se hace para inciar desde el Main con el usuario por defecto en la BD Firebase
            usuarioPresenter = UsuarioPresenterImpl(this)
            getUsuarioDefault()
        }
        return usuario
    }

    override fun showUsuarios(usuarios: ArrayList<Usuario>) {
        TODO("Not yet implemented")
    }

    override fun showUsuarioDefault(usuario: Usuario) {
        val it = extraHelper?.setExtUsuario(context, usuario)
        context?.startActivity(it)
    }

    override fun navigateNavUsuarios() {
        TODO("Not yet implemented")
    }

    override fun getUsuarios(idUsuarioCreador: Int) {
        TODO("Not yet implemented")
    }

    override fun getUsuarioDefault() {
        usuarioPresenter?.getUsuarioDefault()
    }

    override fun setUsuario(usuario: Usuario) {
        TODO("Not yet implemented")
    }

    override fun updateUsuario(usuario: Usuario) {
        TODO("Not yet implemented")
    }

    override fun deleteUsuario(idDocumento: String) {
        TODO("Not yet implemented")
    }


}