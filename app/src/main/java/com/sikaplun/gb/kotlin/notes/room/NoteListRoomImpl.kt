package com.sikaplun.gb.kotlin.notes.room

import com.sikaplun.gb.kotlin.notes.domain.model.NoteEntity
import java.util.*

class NoteListRoomImpl(private val noteDao: NoteDao) : NotesListRoom {

    override fun getNotes(): List<NoteEntity> {
        return noteDao.getNotes()
    }

    override fun getNote(id: String): NoteEntity {
        return noteDao.getNote(id)
    }

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

    override fun sortFromOldToNewNotes() {
        noteDao.sortFromOldToNewNotes()
    }

    override fun sortFromNewToOldNotes() {
        noteDao.sortFromNewToOldNotes()
    }

    override fun sorByDateModifiedNotes() {
        noteDao.sorByDateModifiedNotes()
    }

    override fun searchNotes(str: String):List<NoteEntity>  {
        return noteDao.searchNotes(str)
    }

}