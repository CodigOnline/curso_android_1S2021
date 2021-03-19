package com.codigonline.curso_navigation.models

data class Producto(val nombre: String, val descripcion: String, val precio: Double, val clases: Int) {

    var id: Long = -1

    constructor(id: Long, nombre: String, descripcion: String, precio: Double, clases: Int) : this(nombre, descripcion, precio, clases) {
        this.id = id
    }

    override fun toString(): String {
        return "Producto(id=$id, nombre=$nombre, descripcion=$descripcion, precio=$precio, clases=$clases)"
    }

    /**
     * Funcion que recibe un Any que viene a ser un Object de Java
     * Devuelve un booleano
     */
    override fun equals(other: Any?): Boolean {
        // el triple igual determina si el objeto es exactamente el mismo (clase, valor de atributos y dirección de memoria)
        if (this === other) return true

        //SI LA CLASE ES DIFERENTE DEVOLVEMOS FALSE!
        if (javaClass != other?.javaClass) return false

        // CASTEAMOS OTHER AS PRODUCTO
        other as Producto

        //COMPARAMOS CADA UNO DE SUS ATRIBUTOS

        if (id != other.id) return false


        //AQUI SOLO LLEGAMOS SI LA COMPROBACIÓN DE CADA UNO DE LOS ATRIBUTOS NO HA DADO FALSE
        return true
    }


}
