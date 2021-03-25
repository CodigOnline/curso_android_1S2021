package com.codigonline.curso_navigation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codigonline.curso_navigation.database.entities.Producto
import com.codigonline.curso_navigation.databinding.ItemProductoRecyclerViewBinding


class RecyclerViewAdapter(val productos: MutableList<Producto>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private fun eliminarProducto(producto: Producto) {
        val pos = productos.indexOf(producto)
        productos.removeAt(pos)
        notifyItemRemoved(pos)
    }

    fun actualizarProducto(producto: Producto) {
        val pos = productos.indexOf(producto)
        productos[pos] = producto
        notifyItemChanged(pos)
    }

    fun crearProducto(producto: Producto) {
        productos.add(producto)
        notifyItemInserted(productos.size)
    }


    class ViewHolder private constructor(val binding: ItemProductoRecyclerViewBinding, val adapter: RecyclerViewAdapter) : RecyclerView.ViewHolder(binding.root) {
        fun rellenarDatos(producto: Producto) {
            binding.productoName.text = producto.nombre
            binding.productoClases.text = producto.clases.toString()
            binding.productoDescription.text = producto.descripcion
            binding.productoPrecio.text = producto.precio.toString()

            binding.productoEliminar.setOnClickListener {
                adapter.eliminarProducto(producto)
            }
        }

        companion object {
            fun newInstance(parent: ViewGroup, adapter: RecyclerViewAdapter): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemProductoRecyclerViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding, adapter)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder.newInstance(parent, this)

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