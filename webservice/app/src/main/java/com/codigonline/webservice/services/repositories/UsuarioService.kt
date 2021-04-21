package com.codigonline.webservice.services.repositories

import com.codigonline.webservice.services.entities.Usuario
import retrofit2.Call
import retrofit2.http.*

interface UsuarioService {

    @GET("usuarios/{id}")
    fun getById(@Path("id") id: Int): Call<Usuario.Get>

    @PUT("usuarios/{id}")
    fun updateById(
            @Header("Authorization") token: String,
            @Path("id") id: Int,
            @Body usuario: Usuario.Update
    ): Call<Usuario.Get>

    @DELETE("usuarios/{id}")
    fun deleteById(
            @Header("Authorization") token: String,
            @Path("id") id: Int
    ): Call<Usuario.Delete>


}