package com.utp.comidaencasav1.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.utp.comidaencasav1.R
import com.utp.comidaencasav1.databinding.ActivityCuentaAddBinding
import com.utp.comidaencasav1.helper.ExtraHelper
import com.utp.comidaencasav1.helper.OperacionHelper
import com.utp.comidaencasav1.model.Cuenta
import com.utp.comidaencasav1.presenter.implement.CuentaPresenterImpl
import com.utp.comidaencasav1.presenter.interfaces.CuentaPresenter
import com.utp.comidaencasav1.view.interfaces.CuentaView

class CuentaAddActivity : AppCompatActivity(), CuentaView {

    private lateinit var binding: ActivityCuentaAddBinding

    private var extraHelper: ExtraHelper? = null
    private var cuentaPresenter: CuentaPresenter? = null
    private var edtFamilia: EditText? = null
    private var edtCorreo: EditText? = null
    private var edtConstrasena: EditText? = null
    private var btnRegistrarse: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuenta_add)
        binding = ActivityCuentaAddBinding.inflate(layoutInflater)
        val root: View = binding.root
        setContentView(root)

        initialConfig()

        btnRegistrarse!!.setOnClickListener {
            var cuenta: Cuenta = getComponents()
            setCuenta(cuenta)
        }
    }

    private fun initialConfig() {
        extraHelper = ExtraHelper()
        cuentaPresenter = CuentaPresenterImpl(this)
        edtFamilia = binding.edtFamiliaCuenta
        edtCorreo = binding.edtCorreoCuenta
        edtConstrasena = binding.edtConstrasenaCuenta
        btnRegistrarse = binding.btnRegistrarseCuenta
    }

    private fun getComponents(): Cuenta {
        var cuenta: Cuenta = Cuenta()
        cuenta.familiaNombre = edtFamilia!!.text.toString()
        cuenta.correo = edtCorreo!!.text.toString()
        cuenta.contrasena = OperacionHelper().encryptSHA1(edtConstrasena!!.text.toString())
        return cuenta
    }

    override fun validateInicioSesion(cuenta: Cuenta?) {
        TODO("Not yet implemented")
    }

    override fun navigateLoginActivity() {
        val it = Intent(this, LoginActivity::class.java)
        this.startActivity(it)
    }

    private fun setCuenta(cuenta: Cuenta) {
        cuentaPresenter!!.setCuenta(cuenta)
    }
}