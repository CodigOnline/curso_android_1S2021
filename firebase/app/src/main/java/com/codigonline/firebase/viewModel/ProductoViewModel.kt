package com.codigonline.firebase.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codigonline.firebase.App
import com.codigonline.firebase.entities.Producto
import com.codigonline.firebase.utils.Constantes
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects

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
            .orderBy("nombre",Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { documentos ->
                /*val list = mutableListOf<Producto>()
                for (document in documentos){
                    val producto = document.toObject<Producto>()
                }*/
                val lista = documentos.toObjects<Producto>()
                _productos.value = lista
            }
    }


}