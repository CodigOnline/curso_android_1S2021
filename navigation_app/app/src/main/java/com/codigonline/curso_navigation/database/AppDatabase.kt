package com.codigonline.curso_navigation.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.codigonline.curso_navigation.database.daos.UsuarioDao
import com.codigonline.curso_navigation.database.entities.Usuario

@Database(entities = arrayOf(Usuario::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao //FUNCION ABSTRACTA //UsuarioDao


    companion object {
        private var instance: AppDatabase? = null
        private const val NAME_DB = "curso_kotlin"

        fun newIntance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, AppDatabase::class.java, NAME_DB).build()

            }
            return instance!!
        }

    }
}