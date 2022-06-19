package com.utp.comidaencasav1.view.fragment.cuenta

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utp.comidaencasav1.R

class CuentaUpdateFragment : Fragment() {

    companion object {
        fun newInstance() = CuentaUpdateFragment()
    }

    private lateinit var viewModel: CuentaUpdateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cuenta_update, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CuentaUpdateViewModel::class.java)
        // TODO: Use the ViewModel
    }

}