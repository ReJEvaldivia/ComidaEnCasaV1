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
import com.utp.comidaencasav1.helper.ExtraHelper
import com.utp.comidaencasav1.model.Plato

class PlatosAddUpdateFragment : Fragment() {

    private var _binding: FragmentPlatosAddUpdateBinding? = null
    private val binding get() = _binding!!

    private var extraHelper: ExtraHelper = ExtraHelper()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlatosAddUpdateBinding.inflate(inflater, container, false)
        val root: View = binding.root
        //Recuperar el usuario
        val usuario = extraHelper.getUsuario(requireActivity())

        var idDocumento: String = ""
        var edtNombre: EditText = binding.edtNombrePlatoAU
        var chkEstadoVisibilidad: CheckBox = binding.chkEstadoVisibilidadPlatoAU
        var btnEliminar: Button = binding.btnEliminarPlatoAU
        var btnEditar: Button = binding.btnEditarPlatoAU
        var btnRegistrar: Button = binding.btnRegistrarPlatoAU

        //Recuperar el item
        val arg_plato = arguments?.getSerializable("arg_plato")
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

        //Crear una instancia de Firebase
        val db = FirebaseFirestore.getInstance()
        val platoRef = db.collection("Plato")

        btnRegistrar.setOnClickListener {
            var plato: Plato = Plato()
            plato.idDocumento = idDocumento
            plato.idUsuarioCreador = usuario.idUsuario
            plato.nombre = edtNombre.text.toString()
            plato.estadoVisibilidad = chkEstadoVisibilidad.isChecked

            //INSERT
            platoRef.orderBy("idPlato", Query.Direction.DESCENDING).limit(1).get()
                .addOnSuccessListener { querySnapshot ->

                    val platos = ArrayList(querySnapshot.toObjects<Plato>())
                    var idCount: Int = 0
                    if (platos.size > 0) {
                        idCount =
                            platos[0].idPlato//Recupera el último idPlato registrado en la BD
                    }
                    plato.idPlato = idCount + 1
                    val newPlatoRef = platoRef.document()
                    plato.idDocumento = newPlatoRef.id

                    //platoRef.add(plato)
                    newPlatoRef.set(plato)
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

        btnEditar.setOnClickListener {
            var plato: Plato = Plato()
            plato.idDocumento = idDocumento
            plato.nombre = edtNombre.text.toString()
            plato.estadoVisibilidad = chkEstadoVisibilidad.isChecked

            //UPDATE
            platoRef.document(idDocumento)
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

        btnEliminar.setOnClickListener {
            //DELETE
            platoRef.document(idDocumento)
                .delete().addOnSuccessListener {
                    root.findNavController()
                        .navigate(R.id.nav_platos)
                }
        }

        return root
    }
}