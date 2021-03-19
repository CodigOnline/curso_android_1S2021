package com.codigonline.curso_navigation.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codigonline.curso_navigation.R
import com.codigonline.curso_navigation.adapters.ListViewAdapter
import com.codigonline.curso_navigation.adapters.RecyclerViewAdapter
import com.codigonline.curso_navigation.ui.activities.MainActivity
import com.codigonline.curso_navigation.listeners.MainListener
import com.codigonline.curso_navigation.databinding.FragmentProductosBinding
import com.codigonline.curso_navigation.models.Producto


class ProductosFragment : Fragment() {


    private var listener: MainListener? = null
    private var _binding: FragmentProductosBinding? = null
    private lateinit var mAdapter:RecyclerViewAdapter



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProductosBinding.inflate(inflater, container, false)
        val binding = _binding!!
        val view = binding.root
        //createListView()
        createRecyclerView()

        binding.productosFab.setOnClickListener {
            mAdapter.crearProducto(Producto(15L,"Nuevo producto","Nueva descripción",345678.0,2))
        }




        return view


    }

    private fun createRecyclerView() {
        mAdapter = RecyclerViewAdapter(cargarProductos() as MutableList<Producto>)
        val recyclerView = _binding!!.productosRecyclerView
        recyclerView.apply {
            //EL RECYCLER VIEW VA A SER UNA LISTA VERTICAL
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = mAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        }
    }

    private fun createListView() {
        //CONTEXT
        //LAYOUT DONDE CARGAR LOS DATOS
        //LOS DATOS
        /* val adapter = ArrayAdapter<Producto>(
                 requireContext(),
                 android.R.layout.simple_list_item_1,
                 cargarProductos()
         )*/
        val adapter = ListViewAdapter(requireContext(), R.layout.item_producto_list_view, cargarProductos())
        //_binding!!.productosListView.adapter = adapter
    }

    private fun cargarProductos(): List<Producto> {
        val productos = mutableListOf<Producto>() //LISTA MUTABLE

        productos.add(Producto(1, "individual-mini", "Clases individuales de repaso en informática tamaño mini, 2 clases ", 59.99, 2))
        productos.add(Producto(1, "individual-basic", "Clases individuales de repaso en informática tamaño basic, 4 clases ", 79.99, 4))
        productos.add(Producto(3, "individual-premium", "Clases individuales de repaso en informática tamaño premium, 10 clases ", 119.99, 10))
        productos.add(Producto(4, "grupo-mini", "Clases en grupo de repaso en informática tamaño mini, 2 clases ", 39.99, 2))
        productos.add(Producto(5, "grupo-basic", "Clases en grupo de repaso en informática tamaño basic, 4 clases ", 59.99, 4))
        productos.add(Producto(6, "grupo-premium", "Clases en grupo de repaso en informática tamaño premium, 10 clases ", 99.99, 10))
        productos.add(Producto(7, "proyecto-final", "Tutorización del proyecto de final de grado. 8 clases para conseguir el proyecto que siempre has deseado ", 99.99, 8))
        productos.add(Producto(8, "PAC-M03A", "Ayuda en la realización de la PAC de desarrollo de M03A ", 49.99, 2))
        productos.add(Producto(9, "PAC-M03B", "Ayuda en la realización de la PAC de desarrollo de M03B ", 49.99, 2))
        productos.add(Producto(10, "PAC-DAM-M06", "Ayuda en la realización de la PAC de desarrollo de DAM-M06 ", 49.99, 2))
        productos.add(Producto(11, "PAC-DAM-M07", "Ayuda en la realización de la PAC de desarrollo de DAM-M07", 49.99, 2))
        productos.add(Producto(12, "PAC-DAM-M08", "Ayuda en la realización de la PAC de desarrollo de DAM-M08", 49.99, 2))
        productos.add(Producto(13, "PAC-DAM-M09", "Ayuda en la realización de la PAC de desarrollo de DAM-M09", 49.99, 2))
        productos.add(Producto(14, "curso-android-1S-2021", "Curso de Android + Kotlin, inicio febrero de 2021", 29.99, 0))


        return productos
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as MainActivity
        listener!!.showBottomNavigation()
    }

}