package com.utp.comidaencasav1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObjects
import com.utp.comidaencasav1.databinding.ActivityCuentaBinding
import com.utp.comidaencasav1.model.models.Cuenta
import com.utp.comidaencasav1.model.models.Plato

class CuentaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCuentaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuenta)
        binding = ActivityCuentaBinding.inflate(layoutInflater)
        val root: View = binding.root
        setContentView(root)

        var edtFamilia: EditText = binding.edtFamiliaCuenta
        var edtCorreo: EditText = binding.edtCorreoCuenta
        var edtConstrasena: EditText = binding.edtConstrasenaCuenta
        var btnRegistrarse: Button = binding.btnRegistrarseCuenta

        //Crear una instancia de Firebase
        val db = FirebaseFirestore.getInstance()
        val cuentaRef = db.collection("Cuenta")

        btnRegistrarse.setOnClickListener {
            //INSERT 😎
            var cuenta: Cuenta = Cuenta()
            cuenta.familiaNombre = edtFamilia.text.toString()
            cuenta.correo = edtCorreo.text.toString()
            cuenta.contrasena = edtConstrasena.text.toString()

            cuentaRef.orderBy("idCuenta", Query.Direction.DESCENDING).limit(1).get()
                .addOnSuccessListener { querySnapshot ->
                    val cuentas = ArrayList(querySnapshot.toObjects<Cuenta>())
                    var idCount: Int = 0
                    if (cuentas.size > 0) {
                        idCount =
                            cuentas[0].idCuenta//Recupera el último idCount registrado en la BD
                    }
                    cuenta.idCuenta = idCount + 1

                    cuentaRef.add(cuenta)
                        .addOnSuccessListener {
                            val it = Intent(root.context, LoginActivity::class.java)
                            root.context.startActivity(it)
                        }
                }


        }
    }
}