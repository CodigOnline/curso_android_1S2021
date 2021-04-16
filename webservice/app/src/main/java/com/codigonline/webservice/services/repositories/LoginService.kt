package com.codigonline.webservice.services.repositories

import com.codigonline.webservice.services.entities.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("login")
    fun login(@Body usuario: Usuario.Login): Call<Usuario.Token>
}