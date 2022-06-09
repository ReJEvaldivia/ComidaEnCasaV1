package com.utp.comidaencasav1.ui.insumos

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utp.comidaencasav1.R

class InsumosFragment : Fragment() {

    companion object {
        fun newInstance() = InsumosFragment()
    }

    private lateinit var viewModel: InsumosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_insumos, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InsumosViewModel::class.java)
        // TODO: Use the ViewModel
    }

}