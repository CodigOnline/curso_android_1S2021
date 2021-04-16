package com.codigonline.webservice.ui.perfil

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
import com.codigonline.webservice.databinding.FragmentPerfilBinding
import com.codigonline.webservice.services.entities.Usuario

class PerfilFragment : Fragment() {

    private val perfilViewModel: PerfilViewModel by viewModels()
    private var binding: FragmentPerfilBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPerfilBinding.inflate(inflater, container, false)
        val usuario = Usuario.Update("Alvaro")
        perfilViewModel.actualizar(usuario)
        return binding!!.root
    }
}