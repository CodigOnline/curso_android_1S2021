package com.codigonline.firebase.adapters


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codigonline.firebase.R
import com.codigonline.firebase.databinding.ItemProductoBinding
import com.codigonline.firebase.entities.Producto
import java.util.*


class ProductosRecyclerViewAdapter(var productos: MutableList<Producto>, val context: Context) :
        RecyclerView.Adapter<ProductosRecyclerViewAdapter.ViewHolder>() {

    val originalList = productos

    fun filter(name: String) {
        if (name.isBlank()) {
            productos = originalList
        } else {
            val filter = originalList.filter { producto -> producto.nombre.toLowerCase(Locale.ROOT).contains(name.toLowerCase(Locale.ROOT)) }
            productos = filter as MutableList<Producto>
        }
        notifyDataSetChanged()

    }


    class ViewHolder private constructor(
            val binding: ItemProductoBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun rellenarDatos(producto: Producto, context: Context) {
            binding.productoNombre.text = producto.nombre
            binding.productoClases.text = context.getString(R.string.clases_cart, producto.clases)
            binding.productoDescipcion.text = producto.descripcion
            binding.productoPrecio.text = context.getString(R.string.precio_cart, producto.precio)

        }

        companion object {
            fun newInstance(
                    parent: ViewGroup,
                    adapterProductos: ProductosRecyclerViewAdapter
            ): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemProductoBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder.newInstance(parent, this)

    /* override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder{
         val layoutInflater = LayoutInflater.from(parent.context)
         val binding = ItemProductoRecyclerViewBinding.inflate(layoutInflater, parent, false)
         return ViewHolder(binding)
     }*/
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
            viewHolder.rellenarDatos(productos[position], context)

    override fun getItemCount() = productos.size

/*    override fun getItemCount(): Int {
        return productos.size
    }
    */

}