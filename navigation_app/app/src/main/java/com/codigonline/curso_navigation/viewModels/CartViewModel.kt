package com.codigonline.curso_navigation.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codigonline.curso_navigation.application.App
import com.codigonline.curso_navigation.database.entities.Compra
import com.codigonline.curso_navigation.database.entities.Producto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartViewModel : ViewModel() {

    private val _productosCompra: MutableLiveData<Map<Producto, Int>> =
        MutableLiveData<Map<Producto, Int>>()

    fun obtenerProductos(): LiveData<Map<Producto, Int>> = _productosCompra

    fun addProductoToCart(producto: Producto) {
        val _productos = _productosCompra.value
        val map = if (_productos == null) {
            mutableMapOf()
        } else {
            _productos as MutableMap<Producto, Int>
        }


        if (map[producto] == null) {
            map[producto] = 1
        } else {
            val cantidad = map[producto]!!
            map[producto] = cantidad + 1
        }

        var total = 0;
        map.forEach { (_, cantidad) ->
            total += cantidad
        }


        _productosCompra.postValue(map)


    }

    fun delProductoFromCart(producto: Producto) {

    }


    fun calcularTotalCart(): Double {
        val productos = _productosCompra.value
        var total = 0.0
        productos?.forEach { (producto, cantidad) ->
            total += producto.precio * cantidad
        }
        return total
    }

    fun saveCompra(usuarioId: Long) {

        val db = App.obtenerDB()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val productos = _productosCompra.value
                if (productos != null) {
                    val idCompra = db.compraDao().save(Compra(usuarioId))
                    db.compraDao().saveCompraProductos(idCompra, productos)
                }
            }
        }

    }
}