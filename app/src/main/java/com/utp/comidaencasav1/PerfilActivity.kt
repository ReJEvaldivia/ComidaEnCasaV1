package com.utp.comidaencasav1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import com.utp.comidaencasav1.databinding.ActivityPerfilBinding
import com.utp.comidaencasav1.model.models.Cuenta
import com.utp.comidaencasav1.model.models.Usuario


class PerfilActivity : AppCompatActivity() {

    private var rcvPerfil: RecyclerView? = null
    private lateinit var binding: ActivityPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        val root: View = binding.root
        setContentView(root)

        //BOTÓN
        var btnAgregar: Button = binding.btnAgregarPerfil

        //VIEW
        rcvPerfil = binding.rcvPerfilesPerfil//UI
        rcvPerfil?.layoutManager = GridLayoutManager(this, 2)

        //Crear una instancia de Firebase
        val db = FirebaseFirestore.getInstance()
        val usuarioRef = db.collection("Usuario")

        //Recuperar el item
        val bundle = intent.extras!!
        val ext_cuenta = bundle.get("ext_cuenta")
        val cuenta = ext_cuenta as Cuenta

        if (ext_cuenta != null) {
            //Recupera con filtros
            usuarioRef.whereEqualTo("idCuenta", cuenta.idCuenta).orderBy("idRol").limit(8)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    val usuarios = querySnapshot.toObjects<Usuario>()
                    showPerfiles(usuarios)
                }
        }

        btnAgregar.setOnClickListener {
            val it = Intent(root.context, PerfilCreateActivity::class.java)
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            it.putExtra("ext_cuenta", cuenta)
            root.context.startActivity(it)
        }

    }

    fun showPerfiles(usuarios: List<Usuario>?) {
        try {
            rcvPerfil!!.adapter = PerfilAdapter(
                usuarios,
                R.layout.card_perfil
            )//Llama al CardView y lo setea en el Adapter del ReciclerView
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}