package com.utp.comidaencasav1.adapter.fragment.platos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.utp.comidaencasav1.R
import com.utp.comidaencasav1.model.Plato

class PlatosDetalleAdapter(var platos: ArrayList<Plato>?, var resource: Int) :
    RecyclerView.Adapter<PlatosDetalleAdapter.CardPlatoHolder>() {

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
        holder.edtCantidad.setText("0")
        holder.txtUnidad.setText("kg")

        /*val bundle = bundleOf("arg_item" to plato)

        holder.btnEditar.setOnClickListener(View.OnClickListener {
            it.findNavController().navigate(R.id.nav_platosAddUpdateFragment, bundle)
        })*/
    }

    class CardPlatoHolder(v: View) : RecyclerView.ViewHolder(v) {
        var plato: Plato? = null
        var txtNombre: TextView = v.findViewById(R.id.txtNombrePreparacion_PlatoDetalle)
        var edtCantidad: EditText = v.findViewById(R.id.edtCantidadPrepacion_PlatoDetalle)
        var txtUnidad: TextView = v.findViewById(R.id.txtUnidadPreparacion_PlatoDetalle)
        var btnQuitar: Button = v.findViewById(R.id.btnQuitar_PlatoDetalle)
        var lay: LinearLayout = v.findViewById(R.id.lay_plato_detalle)

        fun setDataCard(plato: Plato?) {
            this.plato = plato
            txtNombre.text = plato?.nombre
        }

    }
}