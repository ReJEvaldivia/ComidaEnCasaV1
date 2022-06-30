package com.utp.comidaencasav1.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.utp.comidaencasav1.databinding.ActivityPerfilAddBinding
import com.utp.comidaencasav1.helper.ExtraHelper
import com.utp.comidaencasav1.model.Cuenta
import com.utp.comidaencasav1.model.Usuario
import com.utp.comidaencasav1.presenter.implement.UsuarioPresenterImpl
import com.utp.comidaencasav1.presenter.interfaces.UsuarioPresenter
import com.utp.comidaencasav1.view.interfaces.UsuarioView

class PerfilAddActivity : AppCompatActivity(), UsuarioView {

    private lateinit var binding: ActivityPerfilAddBinding

    private var extraHelper: ExtraHelper? = null
    private var usuarioPresenter: UsuarioPresenter? = null
    private var cuenta: Cuenta? = null
    private var edtNombre: EditText? = null
    private var btnAgregar: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilAddBinding.inflate(layoutInflater)
        val root: View = binding.root
        setContentView(root)

        initialConfig()

        btnAgregar!!.setOnClickListener {
            var usuario: Usuario = getComponents()
            setUsuario(usuario)
        }
    }

    private fun initialConfig() {
        extraHelper = ExtraHelper()
        usuarioPresenter = UsuarioPresenterImpl(this)
        cuenta = extraHelper!!.getExtCuenta(this)
        edtNombre = binding.edtNombrePerfilCreate
        btnAgregar = binding.btnAgregarPerfilCreate
    }

    private fun getComponents(): Usuario {
        var usuario: Usuario = Usuario()
        usuario.nombre = edtNombre!!.text.toString()
        usuario.idCuenta = cuenta!!.idCuenta
        return usuario
    }

    override fun showUsuarioDefault(usuario: Usuario) {
        TODO("Not yet implemented")
    }

    override fun navigatePerfilActivity() {
        val it = extraHelper!!.setExtCuenta(this, cuenta!!, PerfilActivity::class.java)
        this.startActivity(it)
    }

    override fun showPerfiles(usuarios: List<Usuario>) {
        TODO("Not yet implemented")
    }

    override fun getUsuario(): Usuario {
        TODO("Not yet implemented")
    }

    override fun getUsuarioDefault() {
        TODO("Not yet implemented")
    }

    private fun setUsuario(usuario: Usuario) {
        usuarioPresenter!!.setUsuario(usuario)
    }
}