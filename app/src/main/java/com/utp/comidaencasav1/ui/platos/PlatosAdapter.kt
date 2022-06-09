package com.utp.comidaencasav1.ui.platos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.utp.comidaencasav1.R
import com.utp.comidaencasav1.model.models.Plato

class PlatosAdapter(var platos: ArrayList<Plato>?, var resource: Int) :
    RecyclerView.Adapter<PlatosAdapter.CardPlatoHolder>() {

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

        //Enviar argumentos a otro fragment
        val bundle = bundleOf("arg_plato" to plato)
        holder.btnEditar.setOnClickListener(View.OnClickListener {
            it.findNavController().navigate(R.id.nav_platosAddUpdateFragment, bundle)
        })
    }

    class CardPlatoHolder(v: View) : RecyclerView.ViewHolder(v) {
        public var plato: Plato? = null
        public var txtNombre: TextView = v.findViewById(R.id.txtNombre_Plato)
        public var clay: ConstraintLayout = v.findViewById(R.id.clay_Plato)
        public var btnEditar: Button = v.findViewById(R.id.btnEditar_Plato)
        public var btnDetalle: Button = v.findViewById(R.id.btnDetalle_Plato)

        fun setDataCard(plato: Plato?) {
            this.plato = plato
            txtNombre.text = plato?.idPlato.toString() + " " + plato?.nombre
        }

    }
}