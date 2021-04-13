package com.codigonline.firebase.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codigonline.firebase.App
import com.codigonline.firebase.entities.Producto
import com.codigonline.firebase.utils.Constantes
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.stream.Collectors

class FavoritosViewModel : ViewModel() {

    private val firestore = App.getFirestore()
    private val auth = App.getAuth()

    fun addFav(producto: Producto) {
        firestore.collection(Constantes.USUARIOS).document(auth.currentUser.uid)
            .update(Constantes.FAV, FieldValue.arrayUnion(producto.id))
    }

    fun delFav(producto: Producto) {
        firestore.collection(Constantes.USUARIOS).document(auth.currentUser.uid)
            .update(Constantes.FAV, FieldValue.arrayRemove(producto.id))
    }

    fun findAllFavs(): LiveData<Producto?> {
        val liveData = MutableLiveData<Producto?>()
        firestore.collection(Constantes.USUARIOS).document(App.getAuth().currentUser.uid)
            .get().addOnSuccessListener { usuario ->
                val favs = usuario.data?.get(Constantes.FAV)
                if (favs != null) {
                    viewModelScope.launch {
                        withContext(Dispatchers.IO){
                            for (fav in favs as ArrayList<String>) {
                                firestore.collection(Constantes.PRODUCTOS).document(fav).get()
                                    .addOnSuccessListener {
                                        liveData.value = it.toObject<Producto>()!!
                                    }
                                delay(2000)
                            }
                        }
                    }


                } else {
                    liveData.value = null
                }

            }
        return liveData
    }
}