package com.codigonline.curso_navigation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codigonline.curso_navigation.database.entities.Producto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ProductoViewModel : ViewModel() {

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
                val productos = mutableListOf<Producto>() //LISTA MUTABLE
                productos.add(
                    Producto(
                        1,
                        "individual-mini",
                        "Clases individuales de repaso en informática tamaño mini, 2 clases ",
                        59.99,
                        2
                    )
                )
                productos.add(
                    Producto(
                        1,
                        "individual-basic",
                        "Clases individuales de repaso en informática tamaño basic, 4 clases ",
                        79.99,
                        4
                    )
                )
                productos.add(
                    Producto(
                        3,
                        "individual-premium",
                        "Clases individuales de repaso en informática tamaño premium, 10 clases ",
                        119.99,
                        10
                    )
                )
                productos.add(
                    Producto(
                        4,
                        "grupo-mini",
                        "Clases en grupo de repaso en informática tamaño mini, 2 clases ",
                        39.99,
                        2
                    )
                )
                productos.add(
                    Producto(
                        5,
                        "grupo-basic",
                        "Clases en grupo de repaso en informática tamaño basic, 4 clases ",
                        59.99,
                        4
                    )
                )
                productos.add(
                    Producto(
                        6,
                        "grupo-premium",
                        "Clases en grupo de repaso en informática tamaño premium, 10 clases ",
                        99.99,
                        10
                    )
                )
                productos.add(
                    Producto(
                        7,
                        "proyecto-final",
                        "Tutorización del proyecto de final de grado. 8 clases para conseguir el proyecto que siempre has deseado ",
                        99.99,
                        8
                    )
                )
                productos.add(
                    Producto(
                        8,
                        "PAC-M03A",
                        "Ayuda en la realización de la PAC de desarrollo de M03A ",
                        49.99,
                        2
                    )
                )
                productos.add(
                    Producto(
                        9,
                        "PAC-M03B",
                        "Ayuda en la realización de la PAC de desarrollo de M03B ",
                        49.99,
                        2
                    )
                )
                productos.add(
                    Producto(
                        10,
                        "PAC-DAM-M06",
                        "Ayuda en la realización de la PAC de desarrollo de DAM-M06 ",
                        49.99,
                        2
                    )
                )
                productos.add(
                    Producto(
                        11,
                        "PAC-DAM-M07",
                        "Ayuda en la realización de la PAC de desarrollo de DAM-M07",
                        49.99,
                        2
                    )
                )
                productos.add(
                    Producto(
                        12,
                        "PAC-DAM-M08",
                        "Ayuda en la realización de la PAC de desarrollo de DAM-M08",
                        49.99,
                        2
                    )
                )
                productos.add(
                    Producto(
                        13,
                        "PAC-DAM-M09",
                        "Ayuda en la realización de la PAC de desarrollo de DAM-M09",
                        49.99,
                        2
                    )
                )
                productos.add(
                    Producto(
                        14,
                        "curso-android-1S-2021",
                        "Curso de Android + Kotlin, inicio febrero de 2021",
                        29.99,
                        0
                    )
                )
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