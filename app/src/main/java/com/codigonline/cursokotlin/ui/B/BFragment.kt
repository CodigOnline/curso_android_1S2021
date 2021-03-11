package com.codigonline.cursokotlin.ui.B

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codigonline.cursokotlin.R
import com.codigonline.cursokotlin.databinding.ActivityBBinding
import com.codigonline.cursokotlin.databinding.FragmentBBinding

const val param1 = "BOTON_PULSADO"

class BFragment : Fragment() {

    private var binding: FragmentBBinding? = null
    private var botonPulsado: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments.let {
            botonPulsado = it!!.getInt(param1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBBinding.inflate(inflater, container, false)
        val view = binding!!.root
        
        binding!!.fragmentBTextView.text = getString(R.string.text_fragment_b,botonPulsado)
        // Inflate the layout for this fragment
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null

    }

    companion object {
        @JvmStatic
        fun newInstance(boton: Int) =
            BFragment().apply {
                arguments = Bundle().apply {
                    putInt(param1, boton)
                }
            }
    }
}