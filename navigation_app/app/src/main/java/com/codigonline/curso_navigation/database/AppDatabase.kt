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
import com.codigonline.curso_navigation.database.entities.Producto
import com.codigonline.curso_navigation.database.entities.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Database(entities = arrayOf(Usuario::class, Compra::class, Producto::class), version = 1)
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

        private val callback: Callback = object:Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                GlobalScope.launch {
                    //INSERCION DE DATOS
                    withContext(Dispatchers.IO){
                        App.obtenerDB().usuarioDao().save(Usuario("Álvaro","info@codigonline.com","123456789"))
                    }
                }

            }
        }
    }
}