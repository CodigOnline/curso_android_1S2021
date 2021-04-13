package com.codigonline.firebase.ui.fragments

import ProductoAdapter
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codigonline.firebase.R
import com.codigonline.firebase.databinding.FragmentHomeBinding
import com.codigonline.firebase.entities.Producto
import com.codigonline.firebase.viewModel.FavoritosViewModel
import com.codigonline.firebase.viewModel.ProductoViewModel

class ProductoFragment : Fragment(), ProductoAdapter.ProductoAdapterListener {

    private val model: ProductoViewModel by viewModels()
    private val favModel: FavoritosViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private lateinit var mAdapter: ProductoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val binding = _binding!!
        val view = binding.root
        setHasOptionsMenu(true)
        mAdapter = ProductoAdapter(requireContext(), this)

        model.getProductos().observe(viewLifecycleOwner, {
            createRecyclerView(it)
        })




        return view
    }

    fun createRecyclerView(productos: List<Producto>) {

        mAdapter.ProductoAdapter(productos)
        val rv = _binding!!.rvProductos
        rv.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = mAdapter
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                val searchView = item.actionView as SearchView
                searchView.queryHint = "Nombre del producto"
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {

                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        mAdapter.filter(newText!!)
                        return true
                    }

                })
            }
        }
        return false
    }

    override fun addFav(producto: Producto) {
        favModel.addFav(producto)
    }

    override fun delFav(producto: Producto) {
        favModel.delFav(producto)

    }
}