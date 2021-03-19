package com.codigonline.curso_navigation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codigonline.curso_navigation.databinding.FragmentOpinionesBinding
import com.codigonline.curso_navigation.databinding.FragmentProductosBinding

class OpinionesFragment : Fragment() {
    private var _binding: FragmentOpinionesBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOpinionesBinding.inflate(inflater, container, false)
        val binding = _binding!!
        val view = binding.root


        return view


    }
}