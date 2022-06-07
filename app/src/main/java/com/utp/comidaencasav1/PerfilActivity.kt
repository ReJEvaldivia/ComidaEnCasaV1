package com.utp.comidaencasav1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utp.comidaencasav1.databinding.ActivityPerfilBinding
import com.utp.comidaencasav1.model.models.Plato
import com.utp.comidaencasav1.presenter.PlatoPresenter
import com.utp.comidaencasav1.presenter.PlatoPresenterImpl
import com.utp.comidaencasav1.view.PlatoView

class PerfilActivity : AppCompatActivity(), PlatoView {

    private var _binding: ActivityPerfilBinding? = null
    private val binding get() = _binding!!
    private var platoPresenter: PlatoPresenter? = null
    private var rcvPerfil: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        platoPresenter = PlatoPresenterImpl(this)


        //VIEW
        rcvPerfil = binding.rcvPerfilesPerfil//UI
        rcvPerfil?.layoutManager = GridLayoutManager(this,2)



        getPlatos()
    }


    override fun showPlatos(platos: ArrayList<Plato>?) {
        try {
            rcvPerfil!!.adapter = PerfilAdapter(
                platos,
                R.layout.card_perfil
            )//Llama al CardView y lo setea en el Adapter del ReciclerView
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getPlatos() {
        platoPresenter?.getPlatos()
    }
}