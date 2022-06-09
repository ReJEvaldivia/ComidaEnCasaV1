package com.utp.comidaencasav1.ui.perfil

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utp.comidaencasav1.R

class PerfilAddUpdateFragment : Fragment() {

    companion object {
        fun newInstance() = PerfilAddUpdateFragment()
    }

    private lateinit var viewModel: PerfilAddUpdateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_perfil_add_update, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PerfilAddUpdateViewModel::class.java)
        // TODO: Use the ViewModel
    }

}