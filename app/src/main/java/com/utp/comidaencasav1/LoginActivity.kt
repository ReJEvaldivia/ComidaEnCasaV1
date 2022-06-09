package com.utp.comidaencasav1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import com.utp.comidaencasav1.databinding.ActivityLoginBinding
import com.utp.comidaencasav1.model.models.Cuenta
import com.utp.comidaencasav1.model.models.Plato

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val root: View = binding.root
        setContentView(root)

        var edtCorreo: EditText = binding.edtCorreoLogin
        var edtConstrasena: EditText = binding.edtConstrasenaLogin
        var btnIniciaSesion: Button = binding.btnIniciaSesionLogin
        var btnRegistrarse: Button = binding.btnRegistrarseLogin

        //Crear una instancia de Firebase
        val db = FirebaseFirestore.getInstance()
        val cuentaRef = db.collection("Cuenta")

        btnIniciaSesion.setOnClickListener {

            val correo = edtCorreo.text.toString()
            val contrasena = edtConstrasena.text.toString()

            cuentaRef.whereEqualTo("correo", correo).whereEqualTo("contrasena", contrasena)
                .orderBy("idCuenta").limit(1)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    //Si existe registro con usuario
                    if (querySnapshot.size() > 0) {
                        var cuenta: Cuenta = Cuenta()
                        for (document in querySnapshot) {
                            cuenta = document.toObject<Cuenta>()!!
                            cuenta.idDocumento = document.id
                            break
                        }
                        val it = Intent(root.context, PerfilActivity::class.java)
                        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        it.putExtra("ext_cuenta", cuenta)
                        root.context.startActivity(it)
                    } else {
                        toast()
                    }
                }
        }

        btnRegistrarse.setOnClickListener {
            val it = Intent(root.context, CuentaActivity::class.java)
            root.context.startActivity(it)
        }
    }

    private fun toast() {
        Toast.makeText(this, "Correo o contraseña incorrecta.", Toast.LENGTH_SHORT).show()
    }
}