package com.codigonline.curso_navigation.database.daos

import androidx.room.*
import com.codigonline.curso_navigation.database.entities.Compra
import com.codigonline.curso_navigation.database.entities.Usuario

@Dao
abstract class CompraDao : BaseDao<Compra>() {

    @Query("SELECT * from compras")
    abstract suspend fun findAll(): List<Compra>

}