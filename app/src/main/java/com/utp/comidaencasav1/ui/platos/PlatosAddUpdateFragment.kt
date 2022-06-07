package com.utp.comidaencasav1.ui.platos

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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.utp.comidaencasav1.R
import com.utp.comidaencasav1.databinding.FragmentPlatosAddUpdateBinding
import com.utp.comidaencasav1.model.models.Plato

class PlatosAddUpdateFragment : Fragment() {

    private var _binding: FragmentPlatosAddUpdateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlatosAddUpdateBinding.inflate(inflater, container, false)
        val root: View = binding.root
        var idDocumento: String = ""
        var edtNombre: EditText = binding.edtNombrePlatoAU
        var chkParticipa: CheckBox = binding.chkParticipaPlatoAU
        var btnEliminar: Button = binding.btnEliminarPlatoAU
        var btnEditar: Button = binding.btnEditarPlatoAU
        var btnRegistrar: Button = binding.btnRegistrarPlatoAU

        //Recuperar el item
        val item = arguments?.getSerializable("arg_item")
        if (item != null) {
            //Editar plato
            val plato = item as Plato
            idDocumento = plato.idDocumento
            edtNombre.setText(plato.nombre)
            btnRegistrar.isVisible = false
        } else {
            //Nuevo plato
            btnEliminar.isVisible = false
            btnEditar.isVisible = false
        }

        binding.btnRegistrarPlatoAU.setOnClickListener {

            var plato: Plato = Plato()
            plato.idCuenta = 1
            plato.idUsuarioCreador = 3
            plato.nombre = edtNombre.text.toString()
            plato.estadoVisibilidad = chkParticipa.isChecked

            val db = FirebaseFirestore.getInstance()

            //INSERT 😎
            val platoRef = db.collection("Plato")
                .add(plato)
                .addOnSuccessListener {
                    Log.d(
                        "Firebase Message",
                        "DocumentSnapshot successfully written!"
                    )
                }
                .addOnFailureListener { e ->
                    Log.d(
                        "Firebase Message",
                        "Error writing document",
                        e
                    )
                }

            it.findNavController().navigate(R.id.nav_platos)
        }


        binding.btnEditarPlatoAU.setOnClickListener {

            var plato: Plato = Plato()
            plato.idCuenta = 1
            plato.idUsuarioCreador = 3
            plato.nombre = edtNombre.text.toString()
            plato.estadoVisibilidad = chkParticipa.isChecked

            val db = FirebaseFirestore.getInstance()

            //UPDATE
            val update = db.collection("Plato").document(idDocumento)
                .update("nombre", plato.nombre)

            it.findNavController().navigate(R.id.nav_platos)
        }

        binding.btnEliminarPlatoAU.setOnClickListener {

            val db = FirebaseFirestore.getInstance()
            //DELETE
            db.collection("Plato").document(idDocumento)
                .delete()

            it.findNavController().navigate(R.id.nav_platos)
        }

        return root
    }

    private lateinit var viewModel: PlatosAddUpdateViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlatosAddUpdateViewModel::class.java)
        // TODO: Use the ViewModel
    }

}