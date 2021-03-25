package com.codigonline.curso_navigation.database.daos

import androidx.room.Dao
import androidx.room.Query

@Dao
interface MainDao {
    @Query("SELECT 1")
    suspend fun init(): Int
}