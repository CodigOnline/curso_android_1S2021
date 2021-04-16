package com.codigonline.webservice.application

import android.app.Application
import android.content.Context
import com.codigonline.webservice.services.entities.Usuario
import com.codigonline.webservice.utils.Constantes

class App : Application() {
    init {
        instancia = this
    }

    companion object {
        private var instancia: App? = null
        fun saveUser(usuario: Usuario.All) {
            val prefs = instancia?.getSharedPreferences("settings", Context.MODE_PRIVATE)
            val edit = prefs?.edit()
            edit?.putInt(Constantes.USUARIO_ID, usuario.id)
            edit?.putString(Constantes.USUARIO_EMAIL, usuario.email)
            edit?.putString(Constantes.USUARIO_NOMBRE, usuario.nombre)
            edit?.apply()
        }

        fun saveToken(token: String) {
            val prefs = instancia?.getSharedPreferences("settings", Context.MODE_PRIVATE)
            val edit = prefs?.edit()
            edit?.putString(Constantes.TOKEN, token)
            edit?.apply()
        }

        fun getUsuario(): Usuario.All {
            val prefs = instancia?.getSharedPreferences("settings", Context.MODE_PRIVATE)
            val id = prefs?.getInt(Constantes.USUARIO_ID, 0)
            val email = prefs?.getString(Constantes.USUARIO_EMAIL, null)
            val nombre = prefs?.getString(Constantes.USUARIO_NOMBRE, null)
            return Usuario.All(id!!, email!!, nombre!!)
        }

        fun getToken(): String? {
            val prefs = instancia?.getSharedPreferences("settings", Context.MODE_PRIVATE)
            val token = prefs?.getString(Constantes.TOKEN, null)
            return token
        }

        fun clear() {
            val prefs = instancia?.getSharedPreferences("settings", Context.MODE_PRIVATE)
            val edit = prefs?.edit()
            edit?.clear()?.apply()
        }
    }
}