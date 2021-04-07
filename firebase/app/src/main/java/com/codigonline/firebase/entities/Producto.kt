package com.codigonline.firebase.entities

import java.util.*

data class Producto constructor(val nombre: String, val descripcion: String, val precio: Double, val clases: Int, val createAt: Date,
                                val updateAt: Date) {
    constructor(nombre: String, descripcion: String, precio: Double, clases: Int) :
            this(nombre, descripcion, precio, clases,
                    createAt = Date(System.currentTimeMillis()),
                    updateAt = Date(System.currentTimeMillis()))

    constructor() : this("", "", 0.0, 0, Date(), Date())

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Producto

        if (nombre != other.nombre) return false

        return true
    }

    override fun hashCode(): Int {
        return nombre.hashCode()
    }


}
