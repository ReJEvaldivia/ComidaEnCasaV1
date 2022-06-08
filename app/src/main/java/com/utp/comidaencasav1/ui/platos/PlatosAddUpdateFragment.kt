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
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObjects
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
        var chkEstadoVisibilidad: CheckBox = binding.chkEstadoVisibilidadPlatoAU
        var btnEliminar: Button = binding.btnEliminarPlatoAU
        var btnEditar: Button = binding.btnEditarPlatoAU
        var btnRegistrar: Button = binding.btnRegistrarPlatoAU

        //Recuperar el item
        val item = arguments?.getSerializable("arg_item")
        if (item != null) {
            //Editar plato
            btnRegistrar.isVisible = false

            val plato = item as Plato
            idDocumento = plato.idDocumento
            edtNombre.setText(plato.nombre)
            chkEstadoVisibilidad.isChecked = plato.estadoVisibilidad
        } else {
            //Nuevo plato
            btnEliminar.isVisible = false
            btnEditar.isVisible = false
        }

        //Crear una instancia de Firebase
        val db = FirebaseFirestore.getInstance()

        binding.btnRegistrarPlatoAU.setOnClickListener {
            var plato: Plato = Plato()
            plato.idDocumento = idDocumento
            plato.idCuenta = 1
            plato.idUsuarioCreador = 3
            plato.nombre = edtNombre.text.toString()
            plato.estadoVisibilidad = chkEstadoVisibilidad.isChecked

            //INSERT 😎
            val platoRef = db.collection("Plato")

            platoRef.orderBy("idPlato", Query.Direction.DESCENDING).limit(1).get()
                .addOnSuccessListener { queryDocumentSnapshot ->
                    val platos = ArrayList(queryDocumentSnapshot.toObjects<Plato>())
                    var idPlatoCount: Int = 0
                    if (platos.size > 0) {
                        idPlatoCount =
                            platos[0].idPlato//Recupera el último idPlato registrado en la BD
                    }
                    plato.idPlato = idPlatoCount + 1

                    platoRef.add(plato)
                        .addOnSuccessListener {
                            root.findNavController()
                                .navigate(R.id.nav_platos)//Navegar al Fragment platos
                        }
                        .addOnFailureListener { e ->
                            Log.d(
                                "Firebase Message",
                                "Error writing document",
                                e
                            )
                        }
                }
        }


        binding.btnEditarPlatoAU.setOnClickListener {
            var plato: Plato = Plato()
            plato.idDocumento = idDocumento
            plato.nombre = edtNombre.text.toString()
            plato.estadoVisibilidad = chkEstadoVisibilidad.isChecked

            //UPDATE
            val update = db.collection("Plato").document(idDocumento)
                .update(
                    mapOf(
                        "idDocumento" to plato.idDocumento,
                        "nombre" to plato.nombre,
                        "estadoVisibilidad" to plato.estadoVisibilidad
                    )
                ).addOnSuccessListener {
                    root.findNavController()
                        .navigate(R.id.nav_platos)
                }
        }

        binding.btnEliminarPlatoAU.setOnClickListener {

            //DELETE
            db.collection("Plato").document(idDocumento)
                .delete().addOnSuccessListener {
                    root.findNavController()
                        .navigate(R.id.nav_platos)
                }
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