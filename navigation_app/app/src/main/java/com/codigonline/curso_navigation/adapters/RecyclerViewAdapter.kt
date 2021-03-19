package com.codigonline.curso_navigation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codigonline.curso_navigation.databinding.ItemProductoRecyclerViewBinding
import com.codigonline.curso_navigation.models.Producto

class RecyclerViewAdapter(val productos:List<Producto>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {



    class ViewHolder private constructor(val binding: ItemProductoRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun rellenarDatos(producto: Producto) {
            binding.productoName.text = producto.nombre
            binding.productoClases.text = producto.clases.toString()
            binding.productoDescription.text = producto.descripcion
            binding.productoPrecio.text = producto.precio.toString()

        }

        companion object {
            fun newInstance(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemProductoRecyclerViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder.newInstance(parent)
   /* override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemProductoRecyclerViewBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }*/
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) = viewHolder.rellenarDatos(productos[position])
    override fun getItemCount() = productos.size

    /*    override fun getItemCount(): Int {
            return productos.size
        }
        */
}