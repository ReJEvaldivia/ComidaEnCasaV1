package com.utp.comidaencasav1.view.fragment.insumos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import com.utp.comidaencasav1.R
import com.utp.comidaencasav1.databinding.FragmentInsumosAddUpdateBinding
import com.utp.comidaencasav1.helper.ArgumentoHelper
import com.utp.comidaencasav1.helper.ExtraHelper
import com.utp.comidaencasav1.model.Cuenta
import com.utp.comidaencasav1.model.Insumo
import com.utp.comidaencasav1.model.Usuario
import com.utp.comidaencasav1.presenter.implement.InsumoPresenterImpl
import com.utp.comidaencasav1.presenter.interfaces.InsumoPresenter
import com.utp.comidaencasav1.view.interfaces.InsumoView

class InsumosAddUpdateFragment : Fragment(), InsumoView {

    private var _binding: FragmentInsumosAddUpdateBinding? = null
    private val binding get() = _binding!!

    private var extraHelper: ExtraHelper? = null
    private var argumentoHelper: ArgumentoHelper? = null
    private var insumoPresenter: InsumoPresenter? = null
    private var usuario: Usuario? = null

    //Componentes
    private var idDocumento: String = ""
    private var edtNombre: EditText? = null
    private var edtUnidad: EditText? = null
    private var btnEliminar: Button? = null
    private var btnEditar: Button? = null
    private var btnRegistrar: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInsumosAddUpdateBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initialConfig()

        setComponents()

        btnRegistrar!!.setOnClickListener {
            var insumo: Insumo = getComponents()
            setInsumo(insumo)
        }

        btnEditar!!.setOnClickListener {
            var insumo: Insumo = getComponents()
            updateInsumo(insumo)
        }

        btnEliminar!!.setOnClickListener {
            deleteInsumo(idDocumento)
        }

        return root
    }

    private fun initialConfig() {
        extraHelper = ExtraHelper()
        argumentoHelper = ArgumentoHelper()
        insumoPresenter = InsumoPresenterImpl(this)

        //Recuperar el usuario
        usuario = extraHelper?.getExtUsuario(requireActivity())
        edtNombre = binding.edtNombreInsumoAU
        edtUnidad = binding.edtUnidadInsumoAU
        btnEliminar = binding.btnEliminarInsumoAU
        btnEditar = binding.btnEditarInsumoAU
        btnRegistrar = binding.btnRegistrarInsumoAU
    }

    private fun setComponents() {
        //Recuperar el item
        val arg_insumo = argumentoHelper?.getArgInsumo(arguments)
        if (arg_insumo != null) {
            //Editar insumo
            btnRegistrar!!.isVisible = false

            val insumo = arg_insumo as Insumo

            idDocumento = insumo.idDocumento
            edtNombre!!.setText(insumo.nombre)
            edtUnidad!!.setText(insumo.unidad)
        } else {
            //Nuevo insumo
            btnEliminar!!.isVisible = false
            btnEditar!!.isVisible = false
        }
    }

    private fun getComponents(): Insumo {
        var insumo: Insumo = Insumo()
        insumo.idCuenta = usuario!!.idCuenta
        insumo.idDocumento = idDocumento
        insumo.nombre = edtNombre!!.text.toString()
        insumo.unidad = edtUnidad!!.text.toString()
        return insumo
    }

    override fun showInsumos(insumos: ArrayList<Insumo>) {
        TODO("Not yet implemented")
    }

    override fun navigateInsumosFragment() {
        binding.root.findNavController().navigate(R.id.nav_insumos)
    }

    private fun setInsumo(insumo: Insumo) {
        insumoPresenter?.setInsumo(insumo)
    }

    private fun updateInsumo(insumo: Insumo) {
        insumoPresenter?.updateInsumo(insumo)
    }

    private fun deleteInsumo(idDocumento: String) {
        insumoPresenter?.deleteInsumo(idDocumento)
    }
}