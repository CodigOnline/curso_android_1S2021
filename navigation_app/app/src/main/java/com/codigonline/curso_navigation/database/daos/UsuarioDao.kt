package com.codigonline.curso_navigation.database.daos

import androidx.room.*
import com.codigonline.curso_navigation.database.entities.Usuario

@Dao
abstract class UsuarioDao: BaseDao<Usuario>() {

    @Query("SELECT * from usuarios")
    abstract suspend fun findAll(): List<Usuario>

    @Query("SELECT * from usuarios where email=:emailUsuario")
    abstract suspend fun findOneByEmail(emailUsuario: String): Usuario




}