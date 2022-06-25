package com.utp.comidaencasav1.view.fragment.reporte

import android.icu.text.Transliterator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.formatter.PercentFormatter
import com.utp.comidaencasav1.R
import com.utp.comidaencasav1.databinding.FragmentReporteBinding


class ReporteFragment : Fragment() {

    private var _binding: FragmentReporteBinding? = null
    private val binding get() = _binding!!

    private var pcCategoria: PieChart? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val reporteViewModel = ViewModelProvider(this).get(ReporteViewModel::class.java)

        _binding = FragmentReporteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //VIEW
        pcCategoria = binding.piechartReporte//fragment
        setPieChart()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun setPieChart() {

        val xvalores = ArrayList<String>()
        xvalores.add("Saludable")
        xvalores.add("Balanceado")
        xvalores.add("No saludable")

        val yvalores = ArrayList<Float>()
        yvalores.add(15.0f)
        yvalores.add(5.0f)
        yvalores.add(10.0f)

        val piechartentry = ArrayList<Entry>()
        for ((i, item) in yvalores.withIndex()) {
            piechartentry.add(Entry(item, i))
        }

        /*piechartentry.add(Entry(15.0f,0))
        piechartentry.add(Entry(5.0f,1))
        piechartentry.add(Entry(10.0f,2))*/

        val piedataset = PieDataSet(piechartentry, "")
        val data = PieData(xvalores, piedataset)
        pcCategoria?.data = data
        piedataset.setColors(ColorTemplate.JOYFUL_COLORS)
        piedataset.sliceSpace = 2f
        piedataset.valueTextSize = 20f
        pcCategoria?.setDescription("Categorías")
        pcCategoria?.setDescriptionTextSize(16f)
        pcCategoria?.setDescriptionColor(resources.getColor(R.color.blue_3))
        pcCategoria?.animateXY(1000, 1000)
        pcCategoria?.legend?.textColor = resources.getColor(R.color.blue_2)
        pcCategoria?.legend?.textSize = 16f
    }

}