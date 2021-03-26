package com.codigonline.curso_navigation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codigonline.curso_navigation.application.App
import com.codigonline.curso_navigation.database.entities.Producto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ProductoViewModel : ViewModel() {
    val db = App.obtenerDB()

    /**
     * MutableLiveData<List<Producto>> productos = new MutableLiveData<>()
     *  val productos = MutableLiveData<List<Producto>>()

     */

    private val _productos: MutableLiveData<List<Producto>> by lazy {
        // also --> Quiero hacer algo más, en este caso introducir datos
        MutableLiveData<List<Producto>>().also {
            cargarProductos()
        }
    }

    private fun cargarProductos() {

        //SIMULANDO LA CONEXIÓN A LA BD

        viewModelScope.launch {
            withContext(Dispatchers.IO) { //CONTEXTO DE ENTRADA Y SALIDA
                val productos = db.productoDao().findAll()
                _productos.postValue(productos)  //MAIN THREAD
            }

        }


    }

    /* fun obtenerProductos() = _productos*/
    // PROTECCION PARA EVITAR QUE SE MODIFIQUEN LOS DATOS QUE ENVIAMOS
    fun obtenerProductos(): LiveData<List<Producto>> {
        return _productos;
    }


}