package com.codigonline.curso_navigation.database.entities

import androidx.room.Entity


@Entity(tableName = "productos")
data class Producto(val nombre: String, val descripcion: String, val precio: Double, val clases: Int):BaseEntity() {

    constructor(id: Long, nombre: String, descripcion: String, precio: Double, clases: Int) : this(nombre, descripcion, precio, clases) {
        this.id = id
    }

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