package com.codigonline.webservice.ui.perfil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.codigonline.webservice.R

class PerfilFragment : Fragment() {

    private lateinit var perfilViewModel: PerfilViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        perfilViewModel =
                ViewModelProvider(this).get(PerfilViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_perfil, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        perfilViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}