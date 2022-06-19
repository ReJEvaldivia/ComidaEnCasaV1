package com.utp.comidaencasav1.view.fragment.insumos

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utp.comidaencasav1.R

class InsumosAddUpdateFragment : Fragment() {

    companion object {
        fun newInstance() = InsumosAddUpdateFragment()
    }

    private lateinit var viewModel: InsumosAddUpdateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_insumos_add_update, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InsumosAddUpdateViewModel::class.java)
        // TODO: Use the ViewModel
    }

}