package com.utp.comidaencasav1.adapter.fragment.lista

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.utp.comidaencasav1.R
import com.utp.comidaencasav1.databinding.CardListaBinding
import com.utp.comidaencasav1.databinding.CardPlatosBinding
import com.utp.comidaencasav1.helper.ArgumentoHelper
import com.utp.comidaencasav1.model.Plato

class ListaAdapter(var platos: List<Plato>, var resource: Int) :
    RecyclerView.Adapter<ListaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.card_lista, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var plato = platos.get(position)
        holder.bind(plato)
    }

    override fun getItemCount(): Int {
        return platos.size ?: 0
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = CardListaBinding.bind(view)
        val root: View = binding.root
        var txtNombre: TextView = binding.txtNombreLista

        fun bind(plato: Plato?) {
            txtNombre.text = plato?.nombre
        }
    }

}
