package com.sikaplun.gb.kotlin.notes.utils

import androidx.room.TypeConverter
import java.util.*

class DateConverters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Calendar? {
        val date = Calendar.getInstance()
        if (value != null) {
            date.timeInMillis = value
        }
        return value?.let { date }
    }

    @TypeConverter
    fun dateToTimestamp(date: Calendar?): Long? {
        return date?.time?.time
    }
}