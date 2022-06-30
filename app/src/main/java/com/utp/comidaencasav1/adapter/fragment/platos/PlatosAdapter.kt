package com.utp.comidaencasav1.adapter.fragment.platos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.utp.comidaencasav1.R
import com.utp.comidaencasav1.databinding.CardPlatosBinding
import com.utp.comidaencasav1.helper.ArgumentoHelper
import com.utp.comidaencasav1.model.Plato

class PlatosAdapter(var platos: ArrayList<Plato>, var resource: Int) :
    RecyclerView.Adapter<PlatosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.card_platos, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var plato = platos.get(position)
        holder.bind(plato)
    }

    override fun getItemCount(): Int {
        return platos.size ?: 0
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = CardPlatosBinding.bind(view)
        val root: View = binding.root
        var plato: Plato? = null
        var txtNombre: TextView = binding.txtNombrePlato
        var btnEditar: Button = binding.btnEditarPlato
        var btnDetalle: Button = binding.btnDetallePlato
        var clay: ConstraintLayout = binding.clayPlato

        fun bind(plato: Plato?) {
            //Enviar argumentos a otro fragment
            val bundle = ArgumentoHelper().setArgPlato(plato)
            this.plato = plato
            txtNombre.text = plato?.idPlato.toString() + " " + plato?.nombre

            btnEditar.setOnClickListener(View.OnClickListener {
                it.findNavController().navigate(R.id.nav_platosAddUpdateFragment, bundle)
            })

            btnDetalle.setOnClickListener(View.OnClickListener {
                it.findNavController().navigate(R.id.nav_platosDetalleFragment, bundle)
            })
        }
    }

}