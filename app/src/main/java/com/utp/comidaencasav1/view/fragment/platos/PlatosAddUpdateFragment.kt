package com.utp.comidaencasav1.view.fragment.platos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObjects
import com.utp.comidaencasav1.R
import com.utp.comidaencasav1.databinding.FragmentPlatosAddUpdateBinding
import com.utp.comidaencasav1.helper.ArgumentoHelper
import com.utp.comidaencasav1.helper.ExtraHelper
import com.utp.comidaencasav1.model.Plato
import com.utp.comidaencasav1.presenter.implement.PlatoPresenterImpl
import com.utp.comidaencasav1.presenter.interfaces.PlatoPresenter
import com.utp.comidaencasav1.view.interfaces.PlatoView

class PlatosAddUpdateFragment : Fragment(), PlatoView {

    private var _binding: FragmentPlatosAddUpdateBinding? = null
    private val binding get() = _binding!!

    private var extraHelper: ExtraHelper = ExtraHelper()
    private var argumentoHelper: ArgumentoHelper = ArgumentoHelper()
    private var platoPresenter: PlatoPresenter = PlatoPresenterImpl(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlatosAddUpdateBinding.inflate(inflater, container, false)
        val root: View = binding.root
        //Recuperar el usuario
        val usuario = extraHelper.getExtUsuario(requireActivity())

        var idDocumento: String = ""
        var edtNombre: EditText = binding.edtNombrePlatoAU
        var chkEstadoVisibilidad: CheckBox = binding.chkEstadoVisibilidadPlatoAU
        var btnEliminar: Button = binding.btnEliminarPlatoAU
        var btnEditar: Button = binding.btnEditarPlatoAU
        var btnRegistrar: Button = binding.btnRegistrarPlatoAU

        //Recuperar el item
        val arg_plato = argumentoHelper.getArgPlato(arguments)
        if (arg_plato != null) {
            //Editar plato
            btnRegistrar.isVisible = false

            val plato = arg_plato as Plato
            idDocumento = plato.idDocumento
            edtNombre.setText(plato.nombre)
            chkEstadoVisibilidad.isChecked = plato.estadoVisibilidad
        } else {
            //Nuevo plato
            btnEliminar.isVisible = false
            btnEditar.isVisible = false
        }

        btnRegistrar.setOnClickListener {
            var plato: Plato = Plato()
            plato.idUsuarioCreador = usuario.idUsuario
            plato.nombre = edtNombre.text.toString()
            plato.estadoVisibilidad = chkEstadoVisibilidad.isChecked
            setPlato(plato)
        }

        btnEditar.setOnClickListener {
            var plato: Plato = Plato()
            plato.idDocumento = idDocumento
            plato.nombre = edtNombre.text.toString()
            plato.estadoVisibilidad = chkEstadoVisibilidad.isChecked
            updatePlato(plato)

        }

        btnEliminar.setOnClickListener {
            deletePlato(idDocumento)
        }

        return root
    }



    override fun showPlatos(platos: ArrayList<Plato>) {
        TODO("Not yet implemented")
    }

    override fun navigateNavPlatos() {
        binding.root.findNavController().navigate(R.id.nav_platos)
    }

    override fun getPlatos(idUsuarioCreador: Int) {
        TODO("Not yet implemented")
    }

    override fun setPlato(plato: Plato) {
        platoPresenter?.setPlato(plato)
    }

    override fun updatePlato(plato: Plato) {
        platoPresenter?.updatePlato(plato)
    }

    override fun deletePlato(idDocumento: String) {
        platoPresenter?.deletePlato(idDocumento)
    }
}