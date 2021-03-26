package com.codigonline.curso_navigation.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.codigonline.curso_navigation.application.App
import com.codigonline.curso_navigation.database.converter.Converters
import com.codigonline.curso_navigation.database.daos.CompraDao
import com.codigonline.curso_navigation.database.daos.MainDao
import com.codigonline.curso_navigation.database.daos.ProductoDao
import com.codigonline.curso_navigation.database.daos.UsuarioDao
import com.codigonline.curso_navigation.database.entities.Compra
import com.codigonline.curso_navigation.database.entities.ComprasProductosCrossRef
import com.codigonline.curso_navigation.database.entities.Producto
import com.codigonline.curso_navigation.database.entities.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Database(entities = arrayOf(Usuario::class, Compra::class, Producto::class,ComprasProductosCrossRef::class), version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao //FUNCION ABSTRACTA //UsuarioDao
    abstract fun mainDao(): MainDao
    abstract fun compraDao(): CompraDao
    abstract fun productoDao(): ProductoDao


    companion object {
        private var instance: AppDatabase? = null
        private const val NAME_DB = "curso_kotlin"

        fun newIntance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, AppDatabase::class.java, NAME_DB)
                        .addCallback(callback)
                        .build()
            }
            return instance!!
        }

        private val callback: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                GlobalScope.launch {
                    //INSERCION DE DATOS
                    withContext(Dispatchers.IO) {
                        App.obtenerDB().usuarioDao().save(Usuario("Álvaro", "a@a.com", "1"))
                        App.obtenerDB().productoDao().saveAll(insertProductos())
                    }
                }

            }
        }

        private fun insertProductos(): List<Producto> {
            val productos = mutableListOf<Producto>() //LISTA MUTABLE
            productos.add(
                    Producto(
                            "individual-mini",
                            "Clases individuales de repaso en informática tamaño mini, 2 clases ",
                            59.99,
                            2
                    )
            )
            productos.add(
                    Producto(
                            "individual-basic",
                            "Clases individuales de repaso en informática tamaño basic, 4 clases ",
                            79.99,
                            4
                    )
            )
            productos.add(
                    Producto(
                            "individual-premium",
                            "Clases individuales de repaso en informática tamaño premium, 10 clases ",
                            119.99,
                            10
                    )
            )
            productos.add(
                    Producto(
                            "grupo-mini",
                            "Clases en grupo de repaso en informática tamaño mini, 2 clases ",
                            39.99,
                            2
                    )
            )
            productos.add(
                    Producto(
                            "grupo-basic",
                            "Clases en grupo de repaso en informática tamaño basic, 4 clases ",
                            59.99,
                            4
                    )
            )
            productos.add(
                    Producto(
                            "grupo-premium",
                            "Clases en grupo de repaso en informática tamaño premium, 10 clases ",
                            99.99,
                            10
                    )
            )
            productos.add(
                    Producto(
                            "proyecto-final",
                            "Tutorización del proyecto de final de grado. 8 clases para conseguir el proyecto que siempre has deseado ",
                            99.99,
                            8
                    )
            )
            productos.add(
                    Producto(
                            "PAC-M03A",
                            "Ayuda en la realización de la PAC de desarrollo de M03A ",
                            49.99,
                            2
                    )
            )
            productos.add(
                    Producto(
                            "PAC-M03B",
                            "Ayuda en la realización de la PAC de desarrollo de M03B ",
                            49.99,
                            2
                    )
            )
            productos.add(
                    Producto(
                            "PAC-DAM-M06",
                            "Ayuda en la realización de la PAC de desarrollo de DAM-M06 ",
                            49.99,
                            2
                    )
            )
            productos.add(
                    Producto(
                            "PAC-DAM-M07",
                            "Ayuda en la realización de la PAC de desarrollo de DAM-M07",
                            49.99,
                            2
                    )
            )
            productos.add(
                    Producto(
                            "PAC-DAM-M08",
                            "Ayuda en la realización de la PAC de desarrollo de DAM-M08",
                            49.99,
                            2
                    )
            )
            productos.add(
                    Producto(
                            "PAC-DAM-M09",
                            "Ayuda en la realización de la PAC de desarrollo de DAM-M09",
                            49.99,
                            2
                    )
            )
            productos.add(
                    Producto(
                            "curso-android-1S-2021",
                            "Curso de Android + Kotlin, inicio febrero de 2021",
                            29.99,
                            0
                    )
            )
            return productos
        }
    }
}