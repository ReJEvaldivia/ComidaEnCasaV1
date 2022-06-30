package com.utp.comidaencasav1.view.fragment.platos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utp.comidaencasav1.R
import com.utp.comidaencasav1.adapter.fragment.platos.PlatosDetalleAdapter
import com.utp.comidaencasav1.databinding.FragmentPlatosDetalleBinding
import com.utp.comidaencasav1.model.Plato
import com.utp.comidaencasav1.presenter.interfaces.PlatoPresenter
import com.utp.comidaencasav1.presenter.implement.PlatoPresenterImpl
import com.utp.comidaencasav1.view.interfaces.PlatoView

class PlatosDetalleFragment: Fragment() , PlatoView {

    private var _binding: FragmentPlatosDetalleBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var platoPresenter: PlatoPresenter? = null
    private var rvIngredientes: RecyclerView? = null
    private var spCategoria: Spinner?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlatosDetalleBinding.inflate(inflater, container, false)
        val root: View = binding.root

        platoPresenter = PlatoPresenterImpl(this)

        //VIEW
        rvIngredientes = binding.rcvPlatosPlatoDetalle//UI
        rvIngredientes?.layoutManager = LinearLayoutManager(this.context)
        spCategoria = binding.spnCategoriaPlatoDetalle

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


    override fun showPlatos(platos: ArrayList<Plato>) {
        try {
            rvIngredientes!!.adapter = PlatosDetalleAdapter(
                platos,
                R.layout.card_ingredientes_plato_detalle
            )//Llama al CardView y lo setea en el Adapter del ReciclerView

            //Creamos la lista de nombre platos
            var listPlatoNombre = ArrayList<String>()
            for (i in platos!!) {
                listPlatoNombre.add(i.nombre)
            }
            //Seteamos la lista de nombres de platos en el spinner
            spCategoria!!.adapter = ArrayAdapter<String>(
                binding.spnCategoriaPlatoDetalle!!.context,
                android.R.layout.simple_list_item_1,
                listPlatoNombre
            )

            //Al seleccionar un item recuperamos el idPlato
            spCategoria!!.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    adapterView: AdapterView<*>?,
                    view: View,
                    i: Int,
                    l: Long
                ) {
                    Toast.makeText(
                        binding.spnCategoriaPlatoDetalle!!.context,
                        "Cliked Id :" + platos.get(i).idPlato,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(adapterView: AdapterView<*>?) {}
            })

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun navigatePlatosFragment() {
        TODO("Not yet implemented")
    }

    private fun getPlatos(idUsuarioCreador: Int) {
        platoPresenter?.getPlatos(idUsuarioCreador)
    }
}