package com.codigonline.curso_navigation.database.daos

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.codigonline.curso_navigation.database.entities.BaseEntity
import java.util.*

abstract class BaseDao<T> where T: BaseEntity { //T donde debe de extender de BaseEntity

    @Delete
    abstract fun delete(t:T)

    @Insert
    abstract fun save(t:T)

   @Update
   protected abstract fun updatePrivado(t:T)

    fun update(t:T){
        t.updateAt = Date(System.currentTimeMillis())
        updatePrivado(t)
    }



}