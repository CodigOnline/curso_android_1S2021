package com.codigonline.webservice.ui.articulos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.codigonline.webservice.R

class ArticulosFragment : Fragment() {

    private lateinit var articulosViewModel: ArticulosViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        articulosViewModel =
                ViewModelProvider(this).get(ArticulosViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_articulos, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        articulosViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}