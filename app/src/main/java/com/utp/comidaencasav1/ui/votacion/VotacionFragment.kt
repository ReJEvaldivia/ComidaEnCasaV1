package com.utp.comidaencasav1.ui.votacion

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utp.comidaencasav1.R
import com.utp.comidaencasav1.databinding.FragmentVotacionBinding
import com.utp.comidaencasav1.model.models.Plato
import com.utp.comidaencasav1.presenter.PlatoPresenter
import com.utp.comidaencasav1.presenter.PlatoPresenterImpl
import com.utp.comidaencasav1.ui.platos.PlatosViewModel
import com.utp.comidaencasav1.view.PlatoView

class VotacionFragment : Fragment(), PlatoView {

    private var _binding: FragmentVotacionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var platoPresenter: PlatoPresenter? = null
    private var rvPlatosVotacion: RecyclerView? = null
    private var rvPlatosPuntajeVotacion: RecyclerView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val platosViewModel =
            ViewModelProvider(this).get(PlatosViewModel::class.java)

        _binding = FragmentVotacionBinding.inflate(inflater, container, false)
        val root: View = binding.root

        platoPresenter = PlatoPresenterImpl(this)

        //VIEW
        rvPlatosVotacion = binding.rcvPlatosVotacion//UI
        rvPlatosVotacion?.layoutManager = LinearLayoutManager(this.context)

        //VIEW
        rvPlatosPuntajeVotacion = binding.rcvPuntajeVotacion//UI
        rvPlatosPuntajeVotacion?.layoutManager = LinearLayoutManager(this.context)

        getPlatos()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun showPlatos(platos: ArrayList<Plato>?) {
        try {
            rvPlatosVotacion!!.adapter = VotacionAdapter(
                platos,
                R.layout.card_lista_votacion
            )//Llama al CardView y lo setea en el Adapter del ReciclerView

            rvPlatosPuntajeVotacion!!.adapter = VotacionPuntajeAdapter(
                platos,
                R.layout.card_puntajes_votacion
            )//Llama al CardView y lo setea en el Adapter del ReciclerView
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getPlatos() {
        platoPresenter?.getPlatos()
    }

}