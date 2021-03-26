package com.codigonline.curso_navigation.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.codigonline.curso_navigation.database.entities.Producto

@Dao
abstract class ProductoDao : BaseDao<Producto>() {

    @Query("select * from productos")
    abstract suspend fun findAll(): List<Producto>



}