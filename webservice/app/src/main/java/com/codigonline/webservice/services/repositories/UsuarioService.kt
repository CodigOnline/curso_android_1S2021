package com.codigonline.webservice.services.repositories

import com.codigonline.webservice.services.entities.Usuario
import retrofit2.Call
import retrofit2.http.*

interface UsuarioService {

    @GET("usuarios/{id}")
    fun getUserById(@Path("id") id: Int): Call<Usuario.Get>

    @PUT("usuarios/{id}")
    fun updateUserById(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body usuario: Usuario.Update
    ): Call<Usuario.Get>


}