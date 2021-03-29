package com.codigonline.curso_navigation.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codigonline.curso_navigation.R
import com.codigonline.curso_navigation.adapters.CartRecyclerViewAdapter
import com.codigonline.curso_navigation.database.entities.Producto
import com.codigonline.curso_navigation.databinding.FragmentCartBinding
import com.codigonline.curso_navigation.ui.activities.MainActivity
import com.codigonline.curso_navigation.viewModels.CartViewModel

class CartFragment : Fragment(), CartRecyclerViewAdapter.CartListener {

    val modelCartito: CartViewModel by activityViewModels()
    private lateinit var mAdapterProductos: CartRecyclerViewAdapter

    private var _binding: FragmentCartBinding? = null
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        val binding = _binding!!
        val view = binding.root


        val liveData = modelCartito.obtenerProductos()
        liveData.observe(viewLifecycleOwner, object : Observer<Map<Producto, Int>> { //implementando la interfaz mediante una clase Anonima
            override fun onChanged(it: Map<Producto, Int>) {
                createRecyclerView(it)
                liveData.removeObserver(this)
            }
        })
        updateTotal()
        binding.cartBtnPagar.setOnClickListener {

            val prefs = requireActivity().getPreferences(Context.MODE_PRIVATE)
            val userId = prefs.getLong("idUsuario", -1)
            modelCartito.saveCompra(usuarioId = userId)
        }

        return view


    }

    private fun createRecyclerView(productos: Map<Producto, Int>) {
        mAdapterProductos = CartRecyclerViewAdapter(productos, modelCartito, this, requireContext())
        val recyclerView = _binding!!.cartRv
        recyclerView.apply {
            //EL RECYCLER VIEW VA A SER UNA LISTA VERTICAL
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = mAdapterProductos
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        }
    }

    override fun updateTotal() {
        val binding = _binding!!
        val total = modelCartito.calcularTotalCart()
        binding.cartTvTotal.text = getString(R.string.total_cart, total)
    }

}