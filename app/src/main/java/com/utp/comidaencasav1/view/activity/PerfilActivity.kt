package com.utp.comidaencasav1.view.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utp.comidaencasav1.adapter.activity.PerfilAdapter
import com.utp.comidaencasav1.R
import com.utp.comidaencasav1.databinding.ActivityPerfilBinding
import com.utp.comidaencasav1.helper.ExtraHelper
import com.utp.comidaencasav1.model.Cuenta
import com.utp.comidaencasav1.model.Usuario
import com.utp.comidaencasav1.presenter.implement.UsuarioPresenterImpl
import com.utp.comidaencasav1.presenter.interfaces.UsuarioPresenter
import com.utp.comidaencasav1.view.interfaces.UsuarioView

class PerfilActivity : AppCompatActivity(), UsuarioView {

    private var rcvPerfil: RecyclerView? = null
    private lateinit var binding: ActivityPerfilBinding

    private var extraHelper: ExtraHelper? = null
    private var usuarioPresenter: UsuarioPresenter? = null
    private var btnAgregar: Button? = null
    private var cuenta: Cuenta? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        val root: View = binding.root
        setContentView(root)

        initialConfig()

        getUsuarios()

        btnAgregar!!.setOnClickListener {
            navigatePerfilAddActivity()
        }
    }

    private fun initialConfig() {
        extraHelper = ExtraHelper()
        usuarioPresenter = UsuarioPresenterImpl(this)

        //BOTÓN
        btnAgregar = binding.btnAgregarPerfil

        //VIEW
        rcvPerfil = binding.rcvPerfilesPerfil//UI
        rcvPerfil?.layoutManager = GridLayoutManager(this, 2)

        cuenta = extraHelper!!.getExtCuenta(this)
    }

    override fun showUsuarioDefault(usuario: Usuario) {
        TODO("Not yet implemented")
    }

    override fun navigatePerfilActivity() {
        TODO("Not yet implemented")
    }

    override fun showPerfiles(usuarios: List<Usuario>) {
        try {
            rcvPerfil!!.adapter = PerfilAdapter(
                usuarios,
                R.layout.card_perfil
            )//Llama al CardView y lo setea en el Adapter del ReciclerView
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getUsuario(): Usuario {
        TODO("Not yet implemented")
    }

    private fun navigatePerfilAddActivity() {
        val it = extraHelper!!.setExtCuenta(this, cuenta!!, PerfilAddActivity::class.java)
        this.startActivity(it)
    }

    override fun getUsuarioDefault() {
        TODO("Not yet implemented")
    }

    private fun getUsuarios() {
        usuarioPresenter!!.getUsuarios(cuenta!!.idCuenta)
    }

}