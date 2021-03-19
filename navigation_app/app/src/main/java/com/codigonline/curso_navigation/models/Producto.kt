package com.codigonline.curso_navigation.models

data class Producto(val nombre: String, val descripcion: String, val precio: Double, val clases: Int) {

    var id: Long = -1

    constructor(id: Long, nombre: String, descripcion: String, precio: Double, clases: Int) : this(nombre, descripcion, precio, clases) {
        this.id = id
    }

    override fun toString(): String {
        return "Producto(id=$id, nombre=$nombre, descripcion=$descripcion, precio=$precio, clases=$clases)"
    }


}
