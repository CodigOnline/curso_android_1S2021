import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codigonline.firebase.R
import com.codigonline.firebase.databinding.ItemFavBinding
import com.codigonline.firebase.databinding.ItemProductoBinding
import com.codigonline.firebase.entities.Producto

class FavsAdapter :
    RecyclerView.Adapter<FavsAdapter.ViewHolder>() {

    val lista = mutableListOf<Producto>()

    fun addFav(producto: Producto) {
        lista.add(producto)
        notifyItemInserted(lista.size)
    }

    class ViewHolder(val binding: ItemFavBinding) : RecyclerView.ViewHolder(binding.root) {

        fun enlazar(producto: Producto) {

            binding.productoClases.text = producto.clases.toString()
            binding.productoNombre.text = producto.nombre
            binding.productoDescipcion.text = producto.descripcion

        }

        companion object {
            fun crear(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemFavBinding.inflate(inflater, parent, false)
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