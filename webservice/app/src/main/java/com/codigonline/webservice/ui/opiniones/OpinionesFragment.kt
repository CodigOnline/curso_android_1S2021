package com.codigonline.webservice.ui.opiniones

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codigonline.webservice.R
import com.codigonline.webservice.adapters.OpinionAdapter
import com.codigonline.webservice.databinding.FragmentOpinionesBinding

class OpinionesFragment : Fragment() {

    val opinionesViewModel: OpinionesViewModel by viewModels()
    private lateinit var mAdapter: OpinionAdapter
    private var binding: FragmentOpinionesBinding? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOpinionesBinding.inflate(inflater, container, false)
        mAdapter = OpinionAdapter()

        val rv = binding!!.rvOpiniones
        rv.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = mAdapter
        }
        ItemTouchHelper(mAdapter.itemMover).attachToRecyclerView(rv)

        val view = binding!!.root
        opinionesViewModel.getOpiniones().observe(viewLifecycleOwner, {
            mAdapter.addOpiniones(it.opiniones)
        })

        return view
    }
}