package com.sikaplun.gb.kotlin.notes.utils

import androidx.room.TypeConverter
import java.util.*

class DateConverters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Calendar? {
        return value?.let { Calendar.getInstance() }
    }

    @TypeConverter
    fun dateToTimestamp(date: Calendar?): Long? {
        return date?.time?.time
    }
}