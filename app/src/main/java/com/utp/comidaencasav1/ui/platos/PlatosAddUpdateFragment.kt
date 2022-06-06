package com.utp.comidaencasav1.ui.platos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.utp.comidaencasav1.R
import com.utp.comidaencasav1.model.models.Plato

class PlatosAddUpdateFragment : Fragment() {


    companion object {
        fun newInstance() = PlatosAddUpdateFragment()
    }

    private lateinit var viewModel: PlatosAddUpdateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var v = inflater.inflate(R.layout.fragment_platos_add_update, container, false)

        var edtNombre: EditText = v.findViewById(R.id.edtNombre_PlatoAU)
        var chkParticipa: CheckBox = v.findViewById(R.id.chkParticipa_PlatoAU)
        var btnEliminar: Button = v.findViewById(R.id.btnEliminar_PlatoAU)
        var btnEditar: Button = v.findViewById(R.id.btnEditar_PlatoAU)
        var btnRegistrar: Button = v.findViewById(R.id.btnRegistrar_PlatoAU)

        val item = arguments?.getSerializable("arg_item")
        if (item != null) {
            //Editar plato
            val plato = item as Plato
            edtNombre.setText(plato.nombre)
            //btnRegistrar.isEnabled = true
            //btnRegistrar.isClickable = false
            btnRegistrar.isVisible = false
        } else {
            //Nuevo plato
        }
        return v

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlatosAddUpdateViewModel::class.java)
        // TODO: Use the ViewModel
    }

}