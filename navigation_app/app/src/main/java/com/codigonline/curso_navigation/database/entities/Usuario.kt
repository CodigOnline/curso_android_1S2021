package com.codigonline.curso_navigation.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class Usuario(val nombre: String, val email: String, val password: String) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0


    override fun toString(): String {
        return "Usuario(nombre='$nombre', email='$email', password='$password')"
    }
}
