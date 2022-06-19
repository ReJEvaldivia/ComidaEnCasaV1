package com.utp.comidaencasav1.ui.platodia

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utp.comidaencasav1.R
import com.utp.comidaencasav1.databinding.FragmentPlatoDiaBinding
import com.utp.comidaencasav1.databinding.FragmentPlatosDetalleBinding
import com.utp.comidaencasav1.model.models.Plato
import com.utp.comidaencasav1.presenter.PlatoPresenter
import com.utp.comidaencasav1.presenter.PlatoPresenterImpl
import com.utp.comidaencasav1.ui.platos.PlatosDetalleAdapter
import com.utp.comidaencasav1.ui.platos.PlatosDetalleViewModel
import com.utp.comidaencasav1.view.PlatoView


class PlatoDiaFragment: Fragment() , PlatoView {

    private var _binding: FragmentPlatoDiaBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var platoPresenter: PlatoPresenter? = null
    private var rvIngredientes: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val insumosViewModel =
            ViewModelProvider(this).get(PlatoDiaViewModel::class.java)

        _binding = FragmentPlatoDiaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        platoPresenter = PlatoPresenterImpl(this)

        //VIEW
        rvIngredientes = binding.rcvIngredientesPlatoDia//UI
        rvIngredientes?.layoutManager = LinearLayoutManager(this.context)

        getPlatos(3)

        //val nav = Navigation.createNavigateOnClickListener(R.id.nav_platosAddUpdateFragment)

        /*_binding!!.btnNuevoInsumo.setOnClickListener({
            nav.onClick(it)
        })

        _binding!!.btnGuardarInsumo.setOnClickListener({
            nav.onClick(it)
        })*/

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun showPlatos(platos: ArrayList<Plato>?) {
        try {
            rvIngredientes!!.adapter = PlatoDiaAdapter(
                platos,
                R.layout.card_ingredientes_plato_dia
            )//Llama al CardView y lo setea en el Adapter del ReciclerView


        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getPlatos(idUsuarioCreador: Int) {
        platoPresenter?.getPlatos(idUsuarioCreador)
    }

}