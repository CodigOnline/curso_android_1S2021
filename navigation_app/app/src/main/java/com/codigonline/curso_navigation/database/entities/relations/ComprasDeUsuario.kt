package com.codigonline.curso_navigation.database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.codigonline.curso_navigation.database.entities.Compra
import com.codigonline.curso_navigation.database.entities.Usuario

data class ComprasDeUsuario(
    @Embedded val usuario: Usuario,
    @Relation(
        parentColumn = "id", //el id del usuario,
        entityColumn = "usuario_id"
    )
    val compras: List<Compra>
)
