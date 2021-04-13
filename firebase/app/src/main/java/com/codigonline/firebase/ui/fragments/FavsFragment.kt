package com.codigonline.firebase.ui.fragments

import FavsAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codigonline.firebase.R
import com.codigonline.firebase.databinding.FragmentFavsBinding
import com.codigonline.firebase.viewModel.FavoritosViewModel


class FavsFragment : Fragment() {

    val model: FavoritosViewModel by viewModels()


    private var binding: FragmentFavsBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavsBinding.inflate(inflater, container, false)
        val view = binding!!.root
        val mAdapter = FavsAdapter()
        val rv = binding!!.favsRv
        rv.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = mAdapter
        }

        model.findAllFavs().observe(viewLifecycleOwner, {
            mAdapter.addFav(it!!)
        })
        // Inflate the layout for this fragment
        return view
    }

}