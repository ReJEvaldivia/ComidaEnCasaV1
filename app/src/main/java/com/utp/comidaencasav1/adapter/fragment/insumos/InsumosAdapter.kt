package com.utp.comidaencasav1.adapter.fragment.insumos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.utp.comidaencasav1.R
import com.utp.comidaencasav1.databinding.CardInsumosBinding
import com.utp.comidaencasav1.helper.ArgumentoHelper
import com.utp.comidaencasav1.model.Insumo

class InsumosAdapter(var insumos: ArrayList<Insumo>, var resource: Int) :
    RecyclerView.Adapter<InsumosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.card_insumos, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var insumo = insumos.get(position)
        holder.bind(insumo)
    }

    override fun getItemCount(): Int {
        return insumos.size ?: 0
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = CardInsumosBinding.bind(view)
        val root: View = binding.root
        var insumo: Insumo? = null
        var txtNombre: TextView = binding.txtNombreInsumo
        var edtCantidad: EditText = binding.edtCantidadInsumo
        var txtUnidad: TextView = binding.txtUnidadInsumo
        var btnEditar: Button = binding.btnEditarInsumo
        var lay: LinearLayout = binding.layInsumos


        fun bind(insumo: Insumo?) {
            //Enviar argumentos a otro fragment
            val bundle = ArgumentoHelper().setArgInsumo(insumo)
            this.insumo = insumo
            txtNombre.text = insumo?.idInsumo.toString() + " " + insumo?.nombre
            edtCantidad.setText(""+insumo?.cantidad)
            txtUnidad.text = insumo?.unidad

            btnEditar.setOnClickListener(View.OnClickListener {
                it.findNavController().navigate(R.id.nav_insumosAddUpdateFragment, bundle)
            })

        }
    }

}