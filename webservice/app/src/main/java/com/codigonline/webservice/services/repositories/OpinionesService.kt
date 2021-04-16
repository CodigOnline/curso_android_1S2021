package com.codigonline.webservice.services.repositories

import com.codigonline.webservice.services.entities.Opiniones
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface OpinionesService {

    @GET("/opiniones")
    fun getAll(): Call<Opiniones>

}