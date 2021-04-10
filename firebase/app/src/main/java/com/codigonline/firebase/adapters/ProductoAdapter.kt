import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codigonline.firebase.databinding.ItemProductoBinding
import com.codigonline.firebase.entities.Producto

class ProductoAdapter : RecyclerView.Adapter<ProductoAdapter.ViewHolder>() {

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
        val filter = originalList.filter { producto -> producto.nombre.toLowerCase().contains(nombre.toLowerCase()) }

        lista.clear()
        lista.addAll(filter)
        notifyDataSetChanged()
    }

    fun removeItem(producto:Producto){
        val pos = originalList.indexOf(producto)
        lista.remove(producto)
        notifyItemRemoved(pos)
        //CONNECTAR A LA BD Y ELIMINAR

    }


    class ViewHolder(val binding: ItemProductoBinding) : RecyclerView.ViewHolder(binding.root) {

        fun enlazar(producto: Producto) {

            binding.productoClases.text = producto.clases.toString()
            binding.productoNombre.text = producto.nombre
            binding.productoDescipcion.text = producto.descripcion
            binding.productoPrecio.text = producto.precio.toString()
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
        holder.enlazar(lista[position])
    }

    override fun getItemCount(): Int {
        return lista.size
    }
}