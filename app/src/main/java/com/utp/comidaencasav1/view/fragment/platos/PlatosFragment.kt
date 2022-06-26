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
import com.utp.comidaencasav1.model.Plato
import com.utp.comidaencasav1.model.Usuario
import com.utp.comidaencasav1.presenter.implement.PlatoPresenterImpl
import com.utp.comidaencasav1.presenter.interfaces.PlatoPresenter
import com.utp.comidaencasav1.view.activity.MainActivity
import com.utp.comidaencasav1.view.interfaces.PlatoView


class PlatosFragment : Fragment(), PlatoView {

    private var _binding: FragmentPlatosBinding? = null
    private val binding get() = _binding!!

    private var platoPresenter: PlatoPresenter? = null
    private var rvPlatos: RecyclerView? = null
    private var svBuscar: SearchView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlatosBinding.inflate(inflater, container, false)
        val root: View = binding.root
        var usuario = getUsuario()

        //Instancia con el presentador
        platoPresenter = PlatoPresenterImpl(this)

        //VIEW
        rvPlatos = binding.rcvPlatosPlato//UI
        rvPlatos?.layoutManager = LinearLayoutManager(this.context)
        svBuscar = binding.svBusquedaPlatos

        getPlatos(usuario.idUsuario)

        _binding!!.btnNuevoPlato.setOnClickListener {
            root.findNavController().navigate(R.id.nav_platosAddUpdateFragment)
        }
        //svBuscar!!.setOnQueryTextListener(this)

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
        val bundleExt = requireActivity().intent.extras
        var usuario = Usuario()
        if (bundleExt != null) {
            //Recuperar el usuario
            val ext_usuario = bundleExt!!.get("ext_usuario")
            usuario = ext_usuario as Usuario
            //Listar los platos del usuario
        } else {
            //Esto se hace para inciar desde el Main con el usuario por defecto en la BD Firebase
            //Crear una instancia de Firebase
            val db = FirebaseFirestore.getInstance()
            val usuarioRef = db.collection("Usuario")
            //Recupera con filtros
            usuarioRef.whereEqualTo("idCuenta", 1).orderBy("idRol")
                .orderBy("idUsuario", Query.Direction.ASCENDING).limit(1)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    val usuarios = querySnapshot.toObjects<Usuario>()
                    usuario = usuarios.first()
                    //Enviar extra a otro activity
                    val it = Intent(context, MainActivity::class.java)
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    it.putExtra("ext_usuario", usuario)
                    context?.startActivity(it)
                }
        }
        return usuario
    }


}