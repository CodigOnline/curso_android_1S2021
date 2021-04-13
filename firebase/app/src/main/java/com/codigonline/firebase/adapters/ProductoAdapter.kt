import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codigonline.firebase.R
import com.codigonline.firebase.databinding.ItemProductoBinding
import com.codigonline.firebase.entities.Producto

class ProductoAdapter(val context: Context, val listener: ProductoAdapterListener) :
    RecyclerView.Adapter<ProductoAdapter.ViewHolder>() {

    val lista = mutableListOf<Producto>()
    private lateinit var originalList: List<Producto>

    fun ProductoAdapter(lista: List<Producto>) {
        this.lista.addAll(lista)
        originalList = lista
    }

    fun filter(nombre: String) {
        /*val filter = mutableListOf<Producto>()
        for (producto in originalList) {
            if (producto.nombre.toLowerCase().contains(nombre.toLowerCase()))
                filter.add(producto)
        }*/
        val filter = originalList.filter { producto ->
            producto.nombre.toLowerCase().contains(nombre.toLowerCase())
        }

        lista.clear()
        lista.addAll(filter)
        notifyDataSetChanged()
    }


    fun addFav(producto: Producto) {
        listener.addFav(producto)
    }

    fun delFav(producto: Producto) {
        listener.delFav(producto)
    }


    class ViewHolder(val binding: ItemProductoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun enlazar(producto: Producto, adapter: ProductoAdapter) {

            binding.productoClases.text = producto.clases.toString()
            binding.productoNombre.text = producto.nombre
            binding.productoDescipcion.text = producto.descripcion
            binding.productoPrecio.text = producto.precio.toString()
            if (producto.fav) {
                Log.e("TAG", producto.toString())
                binding.productoPrecio.iconTint = adapter.context.getColorStateList(R.color.red)
            } else {
                binding.productoPrecio.iconTint = adapter.context.getColorStateList(R.color.grey)
            }

            binding.productoPrecio.setOnClickListener {
                if (producto.fav) {
                    producto.fav = false
                    binding.productoPrecio.iconTint =
                        adapter.context.getColorStateList(R.color.grey)
                    //add Fav
                    adapter.delFav(producto)
                } else {
                    producto.fav = true
                    binding.productoPrecio.iconTint = adapter.context.getColorStateList(R.color.red)
                    //delFav
                    adapter.addFav(producto)
                }
            }

        }

        companion object {
            fun crear(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemProductoBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.crear(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.enlazar(lista[position], this)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    interface ProductoAdapterListener {
        fun addFav(producto: Producto)
        fun delFav(producto: Producto)
    }
}