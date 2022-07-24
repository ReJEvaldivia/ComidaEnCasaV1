package com.utp.comidaencasav1.view.fragment.cerrarsesion

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.utp.comidaencasav1.databinding.FragmentCerrarSesionBinding
import com.utp.comidaencasav1.view.activity.LoginActivity

class CerrarSesionFragment : Fragment() {

    private var _binding: FragmentCerrarSesionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCerrarSesionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //Navegar desde un Fragment a un Activity
        activity?.let {
            val intent = Intent(it, LoginActivity::class.java)
            it.startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}