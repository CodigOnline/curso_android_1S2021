package com.codigonline.curso_navigation.database.daos

import androidx.room.*
import com.codigonline.curso_navigation.database.entities.Compra
import com.codigonline.curso_navigation.database.entities.ComprasProductosCrossRef
import com.codigonline.curso_navigation.database.entities.Producto
import com.codigonline.curso_navigation.database.entities.Usuario
import com.codigonline.curso_navigation.database.entities.relations.CompraConProductos

@Dao
abstract class CompraDao : BaseDao<Compra>() {

    @Query("SELECT * from compras")
    abstract suspend fun findAll(): List<Compra>


    @Transaction
    @Query("select * from compras where id = :compraId")
    abstract suspend fun findAllByIdWithProductos(compraId: Long): CompraConProductos


    @Insert
    protected abstract suspend fun saveCrossRef(crossRef: ComprasProductosCrossRef)

    //YA TENDRÉ LA COMPRA GUARDADA
    suspend fun saveCompraProductos(compraId: Long, productos: Map<Producto, Int>) {
        productos.forEach {
            saveCrossRef(ComprasProductosCrossRef(compraId, it.key.id, it.value))
        }

    }


}