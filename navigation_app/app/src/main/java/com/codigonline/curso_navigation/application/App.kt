package com.codigonline.curso_navigation.application

import android.app.Application
import com.codigonline.curso_navigation.database.AppDatabase

class App : Application() {

    companion object {
        private var db: AppDatabase? = null
        //AQUÍ LO DEVUELVO CUANDO HAGA FALTA
        fun obtenerDB(): AppDatabase {
            return db!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        db = AppDatabase.newIntance(applicationContext)//AQUÍ LO INICIALIZO
    }
}