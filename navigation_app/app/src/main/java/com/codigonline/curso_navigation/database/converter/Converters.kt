package com.codigonline.curso_navigation.database.converter

import androidx.room.TypeConverter
import java.util.*

class Converters {

    @TypeConverter
    fun toDate(date: Long?): Date? { //CUANDO RECUPERAMOS DE LA BD CONVERTIMOS EL LONG EN DATE
        return date?.let { //LET --> NO ES NULLO
            Date(it)
        }
    }

    @TypeConverter
    fun fromDate(date: Date): Long { //CUANDO QUEREMOS GUARDAR EN LA BD CONVETIMOS EL DATE EN UN LONG
        return date.time
    }
}