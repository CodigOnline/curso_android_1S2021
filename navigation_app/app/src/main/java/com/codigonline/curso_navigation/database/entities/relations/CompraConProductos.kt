package com.codigonline.curso_navigation.database.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.codigonline.curso_navigation.database.entities.Compra
import com.codigonline.curso_navigation.database.entities.ComprasProductosCrossRef
import com.codigonline.curso_navigation.database.entities.Producto

data class CompraConProductos(
    @Embedded val compra: Compra,
    @Relation(
        parentColumn = "id",
        entity = Producto::class,
        entityColumn = "id",
        associateBy = Junction(
            ComprasProductosCrossRef::class,
            parentColumn = "compra_id",
            entityColumn = "producto_id"
        )
    )
    val productos: List<Producto>
)
