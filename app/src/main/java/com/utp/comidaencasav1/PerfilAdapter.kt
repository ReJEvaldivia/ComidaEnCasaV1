package com.utp.comidaencasav1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.utp.comidaencasav1.model.models.Plato

class PerfilAdapter(var platos: ArrayList<Plato>?, var resource: Int) :
    RecyclerView.Adapter<PerfilAdapter.CardPlatoHolder>() {

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

        var plato1: Plato = Plato()
        plato1.idPlato = 1
        plato1.nombre = "Arroz con pollo 😊"
        holder.imgPerfil
        holder.txtNombre.setText(""+plato1.nombre)
        val bundle = bundleOf("arg_item" to plato1)

        /*holder.btnEditar.setOnClickListener(View.OnClickListener {
            it.findNavController().navigate(R.id.nav_platos_to_platosAddUpdate, bundle)
        })*/
    }

    class CardPlatoHolder(v: View) : RecyclerView.ViewHolder(v) {
        public var plato: Plato? = null
        public var imgPerfil: ImageView = v.findViewById(R.id.imgPerfil_Perfil)
        public var txtNombre: TextView = v.findViewById(R.id.txtNombre_Perfil)
        public var clay: ConstraintLayout = v.findViewById(R.id.clay_Perfil)

        fun setDataCard(plato: Plato?) {
            this.plato = plato
            txtNombre.setText(plato?.idPlato.toString() + ' ' + plato?.nombre)
        }

    }
}