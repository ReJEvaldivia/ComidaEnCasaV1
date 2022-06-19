package com.utp.comidaencasav1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObjects
import com.utp.comidaencasav1.databinding.ActivityPerfilAddBinding
import com.utp.comidaencasav1.model.models.Cuenta
import com.utp.comidaencasav1.model.models.Usuario

class PerfilAddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilAddBinding.inflate(layoutInflater)
        val root: View = binding.root
        setContentView(root)

        var edtNombre: EditText = binding.edtNombrePerfilCreate
        var btnAgregar: Button = binding.btnAgregarPerfilCreate

        //Crear una instancia de Firebase
        val db = FirebaseFirestore.getInstance()
        val usuarioRef = db.collection("Usuario")

        //Recuperar el item
        val bundle = intent.extras!!
        val ext_cuenta = bundle.get("ext_cuenta")
        val cuenta = ext_cuenta as Cuenta

        btnAgregar.setOnClickListener {
            //INSERT 😎
            var usuario: Usuario = Usuario()
            usuario.nombre = edtNombre.text.toString()
            usuario.idCuenta = cuenta.idCuenta

            usuarioRef.orderBy("idUsuario", Query.Direction.DESCENDING)
                .limit(1).get()
                .addOnSuccessListener { querySnapshot ->
                    val usuarios = ArrayList(querySnapshot.toObjects<Usuario>())
                    var idCount: Int = 0
                    if (usuarios.size > 0) {
                        idCount =
                            usuarios[0].idUsuario//Recupera el último idCount registrado en la BD
                    }
                    usuario.idUsuario = idCount + 1

                    usuarioRef.whereEqualTo("idCuenta", cuenta.idCuenta).limit(1)
                        .get()
                        .addOnSuccessListener { querySnapshot ->
                            //Si existe perfil en la cuenta
                            if (querySnapshot.size() > 0) {
                                //Participante
                                usuario.idRol = 2
                            } else {
                                //Cocinero
                                usuario.idRol = 1
                            }
                            usuarioRef.add(usuario)
                                .addOnSuccessListener {
                                    val it = Intent(root.context, PerfilActivity::class.java)
                                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                                    it.putExtra("ext_cuenta", cuenta)
                                    root.context.startActivity(it)
                                }
                        }
                }
        }
    }
}