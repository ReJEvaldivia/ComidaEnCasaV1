package com.utp.comidaencasav1.view.fragment.perfil

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utp.comidaencasav1.R

class PerfilUpdateFragment : Fragment() {

    companion object {
        fun newInstance() = PerfilUpdateFragment()
    }

    private lateinit var viewModel: PerfilUpdateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_perfil_update, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PerfilUpdateViewModel::class.java)
        // TODO: Use the ViewModel
    }

}