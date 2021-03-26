package com.codigonline.curso_navigation.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "compras_productos", primaryKeys = ["compra_id", "producto_id"])
data class ComprasProductosCrossRef(
    @ColumnInfo(name = "compra_id") val compraId: Long,
    @ColumnInfo(name = "producto_id") val productoId: Long,
    val cantidad: Int
)
