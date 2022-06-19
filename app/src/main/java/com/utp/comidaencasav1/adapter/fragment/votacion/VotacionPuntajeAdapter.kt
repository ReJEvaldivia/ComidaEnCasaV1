package com.utp.comidaencasav1.adapter.fragment.votacion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.utp.comidaencasav1.R
import com.utp.comidaencasav1.model.Plato


class VotacionPuntajeAdapter(var platos: ArrayList<Plato>?, var resource: Int) :
    RecyclerView.Adapter<VotacionPuntajeAdapter.CardPlatoHolder>() {

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
        holder.txtPuntaje.setText("0")
        holder.btnDesempate

    }

    class CardPlatoHolder(v: View) : RecyclerView.ViewHolder(v) {
        var plato: Plato? = null
        var txtNombre: TextView = v.findViewById(R.id.txtNombrePlatoPuntaje_Votacion)
        var txtPuntaje: TextView = v.findViewById(R.id.txtPuntaje_Votacion)
        var clay: ConstraintLayout = v.findViewById(R.id.clayPuntaje_Votacion)
        var btnDesempate: Button = v.findViewById(R.id.btnDesempate_Votacion)

        fun setDataCard(plato: Plato?) {
            this.plato = plato
            txtNombre.text = plato?.nombre
        }

    }
}