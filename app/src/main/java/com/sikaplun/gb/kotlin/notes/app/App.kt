package com.sikaplun.gb.kotlin.notes.app

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.sikaplun.gb.kotlin.notes.room.NoteRoomDb

class App : Application() {
    companion object {
        lateinit var instance: App
        @JvmName("getInstance1")
        fun getInstance() = instance

    }

    private lateinit var noteDb: NoteRoomDb

    override fun onCreate() {
        super.onCreate()
        instance = this
        noteDb = Room.databaseBuilder(
            this,
            NoteRoomDb::class.java,
            "noteDb"
        ).allowMainThreadQueries().build()
    }

    fun getNotesDb(): NoteRoomDb {
        return noteDb
    }

}
