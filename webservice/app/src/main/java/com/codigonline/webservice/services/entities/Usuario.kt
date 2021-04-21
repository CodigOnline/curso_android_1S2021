package com.codigonline.webservice.services.entities

sealed class Usuario {
    data class All(val id: Int, val email: String, val nombre: String,val role:String?){
        constructor(id:Int,email:String,nombre: String):this(id,email, nombre, "")
    }
    data class Get(val usuario: All)
    data class Update(val nombre: String)
    data class Login(val email: String, val password: String)
    data class Token(val token:String)
    data class Delete(val msg:String)
}