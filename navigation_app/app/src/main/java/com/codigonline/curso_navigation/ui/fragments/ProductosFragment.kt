package com.codigonline.curso_navigation.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codigonline.curso_navigation.R
import com.codigonline.curso_navigation.adapters.ListViewAdapter
import com.codigonline.curso_navigation.adapters.ProductosRecyclerViewAdapter
import com.codigonline.curso_navigation.database.entities.Producto
import com.codigonline.curso_navigation.ui.activities.MainActivity
import com.codigonline.curso_navigation.listeners.MainListener
import com.codigonline.curso_navigation.databinding.FragmentProductosBinding
import com.codigonline.curso_navigation.viewModels.CartViewModel
import com.codigonline.curso_navigation.viewModels.ProductoViewModel


class ProductosFragment : Fragment() {


    private var listener: MainListener? = null
    private var _binding: FragmentProductosBinding? = null
    private lateinit var mAdapterProductos: ProductosRecyclerViewAdapter
    val modelProductos: ProductoViewModel by viewModels()
    val modelCarrito: CartViewModel by activityViewModels()


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProductosBinding.inflate(inflater, container, false)
        val binding = _binding!!
        val view = binding.root
        // createListView()
        // createRecyclerView()

        //val model = ViewModelProvider(this).get(ProductoViewModel::class.java)


        modelProductos.obtenerProductos().observe(viewLifecycleOwner, { //--> RECIBO NOTIFICACIÓN DE DATOS NUEVOS
            createRecyclerView(it)
        })






        binding.productosFab.setOnClickListener {
            mAdapterProductos.crearProducto(Producto(15L, "Nuevo producto", "Nueva descripción", 345678.0, 2))
        }




        return view


    }

    private fun createRecyclerView(productos: List<Producto>) {
        mAdapterProductos = ProductosRecyclerViewAdapter(productos as MutableList<Producto>,modelCarrito)
        val recyclerView = _binding!!.productosRecyclerView
        recyclerView.apply {
            //EL RECYCLER VIEW VA A SER UNA LISTA VERTICAL
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = mAdapterProductos
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        }
    }

    private fun createListView(productos: List<Producto>) {
        //CONTEXT
        //LAYOUT DONDE CARGAR LOS DATOS
        //LOS DATOS
        /* val adapter = ArrayAdapter<Producto>(
                 requireContext(),
                 android.R.layout.simple_list_item_1,
                 cargarProductos()
         )*/
        val adapter = ListViewAdapter(requireContext(), R.layout.item_producto_list_view, productos)
        //_binding!!.productosListView.adapter = adapter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as MainActivity
        listener!!.showBottomNavigation()
    }

}