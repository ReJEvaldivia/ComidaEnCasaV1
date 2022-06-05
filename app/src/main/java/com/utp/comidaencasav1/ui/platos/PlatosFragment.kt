package com.utp.comidaencasav1.ui.platos

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utp.comidaencasav1.R
import com.utp.comidaencasav1.databinding.FragmentPlatosBinding
import com.utp.comidaencasav1.model.models.Plato
import com.utp.comidaencasav1.presenter.PlatoPresenter
import com.utp.comidaencasav1.presenter.PlatoPresenterImpl
import com.utp.comidaencasav1.ui.platos.PlatosViewModel
import com.utp.comidaencasav1.view.PlatoView

class PlatosFragment : Fragment(), PlatoView {

    private var _binding: FragmentPlatosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var platoPresenter: PlatoPresenter? = null
    private var rvPlatos: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val platosViewModel =
            ViewModelProvider(this).get(PlatosViewModel::class.java)

        _binding = FragmentPlatosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textPlatos
        platosViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        platoPresenter = PlatoPresenterImpl(this)

        //VIEW
        rvPlatos = binding.rvPlatos//UI
        rvPlatos?.layoutManager = LinearLayoutManager(this.context)

        getPlatos()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun showPlatos(platos: ArrayList<Plato>?) {
        try {
            rvPlatos!!.adapter = PlatosAdapter(
                platos,
                R.layout.card_platos
            )//Llama al CardView y lo setea en el Adapter del ReciclerView
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getPlatos() {
        platoPresenter?.getPlatos()
    }

}