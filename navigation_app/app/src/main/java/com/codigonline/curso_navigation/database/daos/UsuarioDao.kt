package com.codigonline.curso_navigation.database.daos

import androidx.room.*
import com.codigonline.curso_navigation.database.entities.Usuario
import com.codigonline.curso_navigation.database.entities.projections.UserProjection
import com.codigonline.curso_navigation.database.entities.projections.UsuarioCompraProjection
import com.codigonline.curso_navigation.database.entities.relations.ComprasDeUsuario

@Dao
abstract class UsuarioDao : BaseDao<Usuario>() {

    @Query("SELECT * from usuarios")
    abstract suspend fun findAll(): List<Usuario>

    @Query("SELECT nombre, email from usuarios")
    abstract suspend fun findAllProjected(): List<UserProjection>

    @Query("SELECT usuarios.nombre, usuarios.email, compras.fechaCompra from usuarios INNER JOIN compras ON usuarios.id=compras.usuario_id")
    abstract suspend fun findAllWithCompraProjected(): List<UsuarioCompraProjection>

    @Query("SELECT * from usuarios where email=:emailUsuario")
    abstract suspend fun findOneByEmail(emailUsuario: String): Usuario


    @Query("SELECT * from usuarios where id=:usuarioId")
    abstract suspend fun findAllByIdWithCompras(usuarioId: Long): ComprasDeUsuario

    @Transaction
    @Query("SELECT * from usuarios")
    abstract suspend fun findAllWithCompras(): List<ComprasDeUsuario> //2 OPERACIONES SELECT DEL USUARIO SELECT DE LAS COMPRAS


}