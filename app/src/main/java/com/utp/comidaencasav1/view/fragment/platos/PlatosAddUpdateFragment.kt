package com.utp.comidaencasav1.view.fragment.platos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.utp.comidaencasav1.R
import com.utp.comidaencasav1.databinding.FragmentPlatosAddUpdateBinding
import com.utp.comidaencasav1.helper.ArgumentoHelper
import com.utp.comidaencasav1.helper.ExtraHelper
import com.utp.comidaencasav1.model.Plato
import com.utp.comidaencasav1.model.Usuario
import com.utp.comidaencasav1.presenter.implement.PlatoPresenterImpl
import com.utp.comidaencasav1.presenter.interfaces.PlatoPresenter
import com.utp.comidaencasav1.view.interfaces.PlatoView

class PlatosAddUpdateFragment : Fragment(), PlatoView {

    private var _binding: FragmentPlatosAddUpdateBinding? = null
    private val binding get() = _binding!!

    private var extraHelper: ExtraHelper? = null
    private var argumentoHelper: ArgumentoHelper? = null
    private var platoPresenter: PlatoPresenter? = null
    private var usuario: Usuario? = null

    //Componentes
    private var idDocumento: String = ""
    private var edtNombre: EditText? = null
    private var chkEstadoVisibilidad: CheckBox? = null
    private var btnEliminar: Button? = null
    private var btnEditar: Button? = null
    private var btnRegistrar: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlatosAddUpdateBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initialConfig()

        setComponents()

        btnRegistrar!!.setOnClickListener {
            var plato: Plato = getComponents()
            setPlato(plato)
        }

        btnEditar!!.setOnClickListener {
            var plato: Plato = getComponents()
            updatePlato(plato)
        }

        btnEliminar!!.setOnClickListener {
            deletePlato(idDocumento)
        }

        return root
    }

    private fun initialConfig() {
        extraHelper = ExtraHelper()
        argumentoHelper = ArgumentoHelper()
        platoPresenter = PlatoPresenterImpl(this)
        //Recuperar el usuario
        usuario = extraHelper?.getExtUsuario(requireActivity())
        edtNombre = binding.edtNombrePlatoAU
        chkEstadoVisibilidad = binding.chkEstadoVisibilidadPlatoAU
        btnEliminar = binding.btnEliminarPlatoAU
        btnEditar = binding.btnEditarPlatoAU
        btnRegistrar = binding.btnRegistrarPlatoAU
    }

    private fun setComponents() {
        //Recuperar el item
        val arg_plato = argumentoHelper?.getArgPlato(arguments)
        if (arg_plato != null) {
            //Editar plato
            btnRegistrar!!.isVisible = false

            val plato = arg_plato as Plato
            idDocumento = plato.idDocumento
            edtNombre!!.setText(plato.nombre)
            chkEstadoVisibilidad!!.isChecked = plato.estadoVisibilidad
        } else {
            //Nuevo plato
            btnEliminar!!.isVisible = false
            btnEditar!!.isVisible = false
        }
    }

    private fun getComponents(): Plato {
        var plato: Plato = Plato()
        plato.idCuenta = usuario!!.idCuenta
        plato.idDocumento = idDocumento
        plato.idUsuarioCreador = usuario!!.idUsuario
        plato.nombre = edtNombre!!.text.toString()
        plato.estadoVisibilidad = chkEstadoVisibilidad!!.isChecked
        return plato
    }

    override fun showPlatos(platos: ArrayList<Plato>) {
        TODO("Not yet implemented")
    }

    override fun navigatePlatosFragment() {
        binding.root.findNavController().navigate(R.id.nav_platos)
    }

    private fun setPlato(plato: Plato) {
        platoPresenter?.setPlato(plato)
    }

    private fun updatePlato(plato: Plato) {
        platoPresenter?.updatePlato(plato)
    }

    private fun deletePlato(idDocumento: String) {
        platoPresenter?.deletePlato(idDocumento)
    }
}