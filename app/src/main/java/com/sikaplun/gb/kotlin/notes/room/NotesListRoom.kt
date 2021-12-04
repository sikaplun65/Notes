package com.sikaplun.gb.kotlin.notes.room

import com.sikaplun.gb.kotlin.notes.domain.model.NoteEntity
import java.util.*

interface NotesListRoom {
    fun getNotes(): List<NoteEntity>
    fun getNote(id: String): NoteEntity
    fun addNote(note: NoteEntity)
    fun updateNote(noteId: String, noteTitle: String, noteDetail: String, date: Calendar)
    fun removeNote(note: NoteEntity)
    fun sortFromOldToNewNotes():List<NoteEntity>
    fun sortFromNewToOldNotes():List<NoteEntity>
    fun sorByDateModifiedNotes():List<NoteEntity>
    fun searchNotes(str: String):List<NoteEntity>
}