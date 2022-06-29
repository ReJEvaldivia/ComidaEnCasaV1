package com.utp.comidaencasav1.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.utp.comidaencasav1.databinding.ActivityLoginBinding
import com.utp.comidaencasav1.helper.ExtraHelper
import com.utp.comidaencasav1.model.Cuenta
import com.utp.comidaencasav1.presenter.implement.CuentaPresenterImpl
import com.utp.comidaencasav1.presenter.interfaces.CuentaPresenter
import com.utp.comidaencasav1.view.interfaces.CuentaView

class LoginActivity : AppCompatActivity(), CuentaView {

    private lateinit var binding: ActivityLoginBinding

    private var extraHelper: ExtraHelper? = null
    private var cuentaPresenter: CuentaPresenter? = null
    private var edtCorreo: EditText? = null
    private var edtConstrasena: EditText? = null
    private var btnIniciaSesion: Button? = null
    private var btnRegistrarse: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val root: View = binding.root
        setContentView(root)

        initialConfig()

        btnIniciaSesion!!.setOnClickListener {
            var cuenta: Cuenta = getComponents()
            getCuenta(cuenta)
        }

        btnRegistrarse!!.setOnClickListener {
            val it = Intent(root.context, CuentaAddActivity::class.java)
            root.context.startActivity(it)
        }
    }

    private fun initialConfig() {
        extraHelper = ExtraHelper()
        cuentaPresenter = CuentaPresenterImpl(this)
        edtCorreo = binding.edtCorreoLogin
        edtConstrasena = binding.edtConstrasenaLogin
        btnIniciaSesion = binding.btnIniciaSesionLogin
        btnRegistrarse = binding.btnRegistrarseLogin
    }

    private fun getComponents(): Cuenta {
        var cuenta: Cuenta = Cuenta()
        cuenta.correo = edtCorreo!!.text.toString()
        cuenta.contrasena = edtConstrasena!!.text.toString()
        return cuenta
    }

    override fun validateInicioSesion(cuenta: Cuenta?) {
        if (cuenta != null) {
            navigatePerfilActivity(cuenta)
        } else {
            toast()
        }
    }

    private fun toast() {
        Toast.makeText(this, "Correo o contraseña incorrecta.", Toast.LENGTH_SHORT).show()
    }

    private fun navigatePerfilActivity(cuenta: Cuenta) {
        val it = extraHelper!!.setExtCuenta(this, cuenta, PerfilActivity::class.java)
        this.startActivity(it)
    }

    override fun navigateLoginActivity() {
        TODO("Not yet implemented")
    }

    override fun getCuenta(cuenta: Cuenta) {
        cuentaPresenter!!.getCuenta(cuenta)
    }

    override fun setCuenta(cuenta: Cuenta) {
        TODO("Not yet implemented")
    }
}