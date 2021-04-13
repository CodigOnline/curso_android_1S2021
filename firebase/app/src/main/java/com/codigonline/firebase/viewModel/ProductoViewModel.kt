package com.codigonline.firebase.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codigonline.firebase.App
import com.codigonline.firebase.entities.Producto
import com.codigonline.firebase.utils.Constantes
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import java.util.stream.Collectors

class ProductoViewModel : ViewModel() {

    private val firestore = App.getFirestore()
    private val _productos: MutableLiveData<List<Producto>> by lazy {
        MutableLiveData<List<Producto>>().also {
            loadProductos()
        }
    }

    fun getProductos(): LiveData<List<Producto>> {
        return _productos
    }

    private fun loadProductos() {
        firestore.collection(Constantes.PRODUCTOS)
            .orderBy("precio", Query.Direction.ASCENDING)
            .orderBy("nombre", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { documentos ->
                var list = mutableListOf<Producto>()
                for (document in documentos) {
                    val producto = document.toObject<Producto>()
                    producto.id = document.id
                    list.add(producto)
                }

                firestore.collection(Constantes.USUARIOS).document(App.getAuth().currentUser.uid)
                    .get().addOnSuccessListener { usuario ->
                        val favs = usuario.data?.get(Constantes.FAV)
                        if (favs != null) {
                            for (fav in favs as ArrayList<String>) {
                                list = list.stream().map { p ->
                                    if (p.id == fav) {
                                        p.fav = true
                                    }; p
                                }.collect(
                                    Collectors.toList()
                                )
                            }
                            _productos.value = list
                        } else {
                            _productos.value = list
                        }

                    }


            }
    }


}