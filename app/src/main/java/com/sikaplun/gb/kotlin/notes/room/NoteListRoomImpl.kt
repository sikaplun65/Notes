package com.sikaplun.gb.kotlin.notes.room

import com.sikaplun.gb.kotlin.notes.domain.model.NoteEntity
import java.util.*

class NoteListRoomImpl(private val noteDao: NoteDao) : NotesListRoom {

    override fun getNotes(): List<NoteEntity> = noteDao.getNotes()

    override fun getNote(id: String): NoteEntity = noteDao.getNote(id)

    override fun addNote(note: NoteEntity) {
        noteDao.addNote(note)
    }

    override fun updateNote(noteId: String, noteTitle: String, noteDetail: String, date: Calendar) {
        noteDao.updateNote(noteId,noteTitle,noteDetail,date)
    }

    override fun removeNote(note: NoteEntity) {
        val idForDelete = note.id
        noteDao.removeNote(idForDelete)
    }

    override fun sortFromOldToNewNotes(): List<NoteEntity> = noteDao.sortFromOldToNewNotes()

    override fun sortFromNewToOldNotes(): List<NoteEntity> = noteDao.sortFromNewToOldNotes()

    override fun sorByDateModifiedNotes(): List<NoteEntity> = noteDao.sorByDateModifiedNotes()

    override fun searchNotes(str: String):List<NoteEntity> =  noteDao.searchNotes(str)

}