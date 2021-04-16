package com.codigonline.webservice.utils

import android.app.Application
import android.util.Log
import com.codigonline.webservice.application.App
import com.codigonline.webservice.services.entities.Usuario
import com.google.gson.Gson
import java.util.*

class JwtUtils {
    companion object {
        fun decode(token: String) {
            val partes = token.split('.')
            val payload = partes[1]
            val decoder = Base64.getDecoder()
            val json = String(decoder.decode((payload)))
            val gson = Gson()
            val usuario = gson.fromJson(json, Usuario.All::class.java)
            App.saveToken(token)
            App.saveUser(usuario)
            Log.d("TAG", usuario.toString())
        }
    }
}