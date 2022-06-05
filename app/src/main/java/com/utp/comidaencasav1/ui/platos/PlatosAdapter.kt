package com.utp.comidaencasav1.ui.platos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.utp.comidaencasav1.R
import com.utp.comidaencasav1.model.models.Plato

class PlatosAdapter(var platos: ArrayList<Plato>?, var resource: Int) :
    RecyclerView.Adapter<PlatosAdapter.CardPlatoHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CardPlatoHolder {
        var view: View = LayoutInflater.from(p0!!.context).inflate(resource, p0, false)
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
        private var plato: Plato? = null
        private var txtPlato: TextView = v.findViewById(R.id.txtPlato)

        fun setDataCard(plato: Plato?) {
            this.plato = plato
            txtPlato.setText(plato?.idPlato.toString() + ' ' + plato?.nombre)
        }
    }
}