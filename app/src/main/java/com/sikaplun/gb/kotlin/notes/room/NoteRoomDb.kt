package com.sikaplun.gb.kotlin.notes.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sikaplun.gb.kotlin.notes.domain.model.NoteEntity
import com.sikaplun.gb.kotlin.notes.utils.DateConverters

@Database(
    entities = [NoteEntity::class],
    version = 1
)
@TypeConverters(DateConverters::class)
abstract class NoteRoomDb: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}