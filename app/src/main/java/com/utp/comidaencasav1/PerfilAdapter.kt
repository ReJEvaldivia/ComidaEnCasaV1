package com.utp.comidaencasav1

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.utp.comidaencasav1.databinding.ActivityPerfilBinding
import com.utp.comidaencasav1.databinding.CardPerfilBinding
import com.utp.comidaencasav1.model.models.Usuario

class PerfilAdapter(var usuarios: List<Usuario>?, var resource: Int) :
    RecyclerView.Adapter<PerfilAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var usuario = usuarios?.get(position)
        holder.bind(usuario)
        holder.clay.setOnClickListener {
            val it = Intent(holder.root.context, MainActivity::class.java)
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            it.putExtra("ext_usuario", usuario)
            holder.root.context.startActivity(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.card_perfil, parent, false))
    }

    override fun getItemCount(): Int {
        return usuarios?.size ?: 0
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = CardPerfilBinding.bind(view)
        val root: View = binding.root
        var usuario: Usuario? = null
        var imgPerfil: ImageView = binding.imgPerfilPerfil
        var txtNombre: TextView = binding.txtNombrePerfil
        var clay: ConstraintLayout = binding.clayPerfil

        fun bind(usuario: Usuario?) {
            this.usuario = usuario
            txtNombre.setText(usuario?.nombre)
        }
    }
}