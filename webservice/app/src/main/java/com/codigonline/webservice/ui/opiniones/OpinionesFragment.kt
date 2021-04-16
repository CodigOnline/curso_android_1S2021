package com.codigonline.webservice.ui.opiniones

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.codigonline.webservice.R

class OpinionesFragment : Fragment() {

    val opinionesViewModel:OpinionesViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_opiniones, container, false)
        opinionesViewModel.getOpiniones().observe(viewLifecycleOwner,{

        })

        return root
    }
}