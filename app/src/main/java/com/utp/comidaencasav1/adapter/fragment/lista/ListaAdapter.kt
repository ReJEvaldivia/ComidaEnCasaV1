package com.utp.comidaencasav1.adapter.fragment.lista

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.utp.comidaencasav1.R
import com.utp.comidaencasav1.model.Plato


class ListaAdapter(var platos: ArrayList<Plato>?, var resource: Int) :
    RecyclerView.Adapter<ListaAdapter.CardPlatoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CardPlatoHolder {
        var view: View = LayoutInflater.from(parent!!.context).inflate(resource, parent, false)
        return CardPlatoHolder(view)

    }

    override fun getItemCount(): Int {
        return platos?.size ?: 0
    }

    override fun onBindViewHolder(holder: CardPlatoHolder, position: Int) {
        var plato = platos?.get(position)
        holder.setDataCard(plato)


    }

    class CardPlatoHolder(v: View) : RecyclerView.ViewHolder(v) {
        var plato: Plato? = null
        var txtNombre: TextView = v.findViewById(R.id.txtNombre_Lista)
        var clay: ConstraintLayout = v.findViewById(R.id.clay_Lista)

        fun setDataCard(plato: Plato?) {
            this.plato = plato
            txtNombre.text = plato?.nombre
        }

    }
}