package com.codigonline.curso_navigation.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


// CODIGO --> camelCase
//BD --> snake_case
@Entity(tableName = "compras")
data class Compra(val fechaCompra: Date, @ColumnInfo(name = "usuario_id") val usuarioId: Long):BaseEntity(){

}
