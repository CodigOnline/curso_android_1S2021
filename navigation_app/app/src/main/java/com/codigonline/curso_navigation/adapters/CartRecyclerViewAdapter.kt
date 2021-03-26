package com.codigonline.curso_navigation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codigonline.curso_navigation.database.entities.Producto
import com.codigonline.curso_navigation.databinding.ItemCartRecyclerViewBinding
import com.codigonline.curso_navigation.databinding.ItemProductoRecyclerViewBinding
import com.codigonline.curso_navigation.viewModels.CartViewModel
import java.util.AbstractMap


class CartRecyclerViewAdapter(val productos: Map<Producto, Int>, val model: CartViewModel) :
    RecyclerView.Adapter<CartRecyclerViewAdapter.ViewHolder>() {

    fun addToCart(product: Producto) {

    }

    class ViewHolder private constructor(
        val binding: ItemCartRecyclerViewBinding,
        val adapterProductos: CartRecyclerViewAdapter
    ) : RecyclerView.ViewHolder(binding.root) {
        fun rellenarDatos(entry: Map.Entry<Producto, Int>) {
            binding.cartTvName.text = entry.key.nombre
            binding.cartTvClases.text = entry.key.clases.toString()
            binding.cartTvPrecio.text = entry.key.precio.toString()
            binding.cartTvCantidad.text = entry.value.toString()

            binding.cartItemMas.setOnClickListener {
                adapterProductos.addToCart(entry.key)
            }
        }

        companion object {
            fun newInstance(
                parent: ViewGroup,
                adapterProductos: CartRecyclerViewAdapter
            ): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCartRecyclerViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding, adapterProductos)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder.newInstance(parent, this)

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val keys = productos.keys.toList()
        val values = productos.values.toList()
        val entry = AbstractMap.SimpleEntry(keys[position], values[position])
        viewHolder.rellenarDatos(entry)
    }
    
    override fun getItemCount() = productos.keys.size
}