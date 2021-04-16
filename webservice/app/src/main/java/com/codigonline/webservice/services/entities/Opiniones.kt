package com.codigonline.webservice.services.entities

data class Opiniones(val opiniones:List<Opinion>)
data class Opinion(val id:Int, val nombre:String, val descripcion:String, val puntuacion:Int)
