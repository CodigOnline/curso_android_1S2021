package com.codigonline.curso_navigation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codigonline.curso_navigation.R
import com.codigonline.curso_navigation.database.entities.Producto
import com.codigonline.curso_navigation.databinding.ItemProductoRecyclerViewBinding
import com.codigonline.curso_navigation.viewModels.CartViewModel


class ProductosRecyclerViewAdapter(val productos: MutableList<Producto>, val cart: CartViewModel,val context: Context) :
    RecyclerView.Adapter<ProductosRecyclerViewAdapter.ViewHolder>() {

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


    class ViewHolder private constructor(
        val binding: ItemProductoRecyclerViewBinding,
        val adapterProductos: ProductosRecyclerViewAdapter
    ) : RecyclerView.ViewHolder(binding.root) {
        fun rellenarDatos(producto: Producto,context:Context) {
            binding.productoNombre.text = producto.nombre
            binding.productoClases.text = context.getString(R.string.clases_cart,producto.clases)
            binding.productoDescipcion.text = producto.descripcion
            binding.productoPrecio.text = context.getString(R.string.precio_cart,producto.precio)

            binding.productoEliminar.setOnClickListener {
                adapterProductos.eliminarProducto(producto)
            }
            binding.productoPrecio.setOnClickListener {
                adapterProductos.cart.addProductoToCart(producto)
            }
        }

        companion object {
            fun newInstance(
                parent: ViewGroup,
                adapterProductos: ProductosRecyclerViewAdapter
            ): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemProductoRecyclerViewBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding, adapterProductos)
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
        viewHolder.rellenarDatos(productos[position],context)

    override fun getItemCount() = productos.size

    /*    override fun getItemCount(): Int {
            return productos.size
        }
        */
}