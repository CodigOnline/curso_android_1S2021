package com.codigonline.webservice.ui.opiniones

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.codigonline.webservice.R

class OpinionesFragment : Fragment() {

    private lateinit var opinionesViewModel: OpinionesViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        opinionesViewModel =
                ViewModelProvider(this).get(OpinionesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_opiniones, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        opinionesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}