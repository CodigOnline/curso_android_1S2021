package com.codigonline.curso_navigation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codigonline.curso_navigation.R
import com.codigonline.curso_navigation.database.entities.Producto
import com.codigonline.curso_navigation.databinding.ItemCartRecyclerViewBinding
import com.codigonline.curso_navigation.databinding.ItemProductoRecyclerViewBinding
import com.codigonline.curso_navigation.viewModels.CartViewModel
import java.util.AbstractMap


class CartRecyclerViewAdapter(val productos: Map<Producto, Int>, val model: CartViewModel, val listener: CartListener, var context: Context) :
        RecyclerView.Adapter<CartRecyclerViewAdapter.ViewHolder>() {

    interface CartListener {
        fun updateTotal()
    }

    fun addToCart(product: Producto) {
        model.addProductoToCart(product)
        //DEL MAPA ObteNEMOS LAS LLAVES (CADA LLAVE ES UN PRODUCTO
        //CONVERTIMOS LAS LLAVES A UNA LISTA
        //INDEX OF NOS DEVUELVE LA POSICION DEL ELEMENTO EN LA LISTA
        val pos = productos.keys.toList().indexOf(product)
        notifyItemChanged(pos)
        listener.updateTotal()
    }

    fun delFromCart(product: Producto) {
        val pos = productos.keys.toList().indexOf(product)
        var cantidad = productos[product]!!
        model.delProductoFromCart(product)
        if (cantidad == 1) {
            notifyItemRemoved(pos)
        } else {
            notifyItemChanged(pos)
        }
        listener.updateTotal()

    }

    class ViewHolder private constructor(
            val binding: ItemCartRecyclerViewBinding,
            val adapterProductos: CartRecyclerViewAdapter
    ) : RecyclerView.ViewHolder(binding.root) {
        fun rellenarDatos(entry: Pair<Producto, Int>, context: Context) {
            binding.cartTvName.text = entry.first.nombre
            binding.cartTvClases.text = context.getString(R.string.cantidad_clases, entry.first.clases)
            binding.cartTvPrecio.text = entry.first.precio.toString()
            binding.cartTvCantidad.text = entry.second.toString()

            binding.cartItemMas.setOnClickListener {
                adapterProductos.addToCart(entry.first)
            }
            binding.cartItemMenos.setOnClickListener {
                adapterProductos.delFromCart(entry.first)
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
        viewHolder.rellenarDatos(productos.toList()[position], context)
    }

    override fun getItemCount() = productos.keys.size
}