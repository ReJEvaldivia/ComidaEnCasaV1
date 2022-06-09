package com.utp.comidaencasav1.ui.cuenta

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utp.comidaencasav1.R

class CuentaAddUpdateFragment : Fragment() {

    companion object {
        fun newInstance() = CuentaAddUpdateFragment()
    }

    private lateinit var viewModel: CuentaAddUpdateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cuenta_add_update, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CuentaAddUpdateViewModel::class.java)
        // TODO: Use the ViewModel
    }

}