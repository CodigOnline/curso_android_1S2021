package com.codigonline.curso_navigation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codigonline.curso_navigation.databinding.FragmentProductosBinding


class ProductosFragment : Fragment() {


    private var listener: MainListener? = null
    private var _binding: FragmentProductosBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProductosBinding.inflate(inflater, container, false)
        val binding = _binding!!
        val view = binding.root


        return view


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as MainActivity
        listener!!.showBottomNavigation()
    }

}