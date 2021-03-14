package com.codigonline.curso_navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.codigonline.curso_navigation.databinding.FragmentInitBinding

class InitFragment : Fragment() {


    private var binding: FragmentInitBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInitBinding.inflate(inflater, container, false)
        val view = binding!!.root

        binding!!.initToLogin.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_to_loginFragment)
        }

        return view
    }

}