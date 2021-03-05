package com.codigonline.cursokotlin

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.codigonline.cursokotlin.databinding.FragmentPlanetaBinding


class PlanetaFragment : Fragment() {

    private lateinit var binding: FragmentPlanetaBinding
    var listener: PlanetaListener? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlanetaBinding.inflate(layoutInflater)
        val view = binding.root
        binding.button.setOnClickListener {
            val planeta = binding.fragmentPlanetaEt.text.toString()
            listener?.guardarPlaneta(planeta)
        }
        return view
    }

    interface PlanetaListener {
        fun guardarPlaneta(nombre: String)
    }


    // Vincular el fragment con la actividad mediante el listener
    // La actividad que está vinculada al fragment (como está implementa el PlanetaListener)
    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as PlanetaListener //Hacemos un cast para poder obtener las funciones deseadas --> guardarPlaneta
    }
}