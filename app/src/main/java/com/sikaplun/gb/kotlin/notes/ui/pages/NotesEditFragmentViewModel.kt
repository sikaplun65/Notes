package com.sikaplun.gb.kotlin.notes.ui.pages

import androidx.lifecycle.ViewModel
import com.sikaplun.gb.kotlin.notes.app.App
import com.sikaplun.gb.kotlin.notes.domain.model.NoteEntity
import com.sikaplun.gb.kotlin.notes.room.NotesListRoom
import com.sikaplun.gb.kotlin.notes.room.NoteListRoomImpl
import java.util.*

class NotesEditFragmentViewModel() : ViewModel() {

    private val noteDb = App.getInstance().getNotesDb()
    private var notesList: NotesListRoom = NoteListRoomImpl(noteDb.noteDao())
    private lateinit var id: String

    fun onClickSaveButton(
        titleEditText: String,
        detailEditText: String,
        tempTitle: String,
        tempDetail: String,
        noteId: String
    ) {
        id = noteId
        var isModifiedDate = true

        if (id.isEmpty()) {
            createNote(tempTitle,tempDetail)
            isModifiedDate = false
        }

        if (isModifiedDate) {
            if (!tempTitle.equals(notesList.getNote(id).title)
                || !tempDetail.equals(notesList.getNote(id).detail)
            ) {
                notesList.getNote(id).setModifiedDate()
            }
        }

        if (tempTitle.isNotEmpty() || tempDetail.isNotEmpty()) {
            notesList.updateNote(id,tempTitle,tempDetail, Calendar.getInstance())
        }

        createTitleIfEmptyTitle(titleEditText, detailEditText)
        removeNoteIfEmpty(titleEditText, detailEditText)

    }

    private fun removeNoteIfEmpty(titleEditText: String, detailEditText: String) {
        if (titleEditText.isEmpty() && detailEditText.isEmpty()) {
            notesList.removeNote(notesList.getNote(id))
        }
    }

    private fun createTitleIfEmptyTitle(titleEditText: String, detailEditText: String) {
        if (titleEditText.isEmpty() && detailEditText.isNotEmpty()) {
            notesList.getNote(id).title = if (detailEditText.length < 10)
                detailEditText
            else detailEditText.substring(0, 10)
        }
    }

    private fun createNote(title:String,detail:String) {
        val newNote = NoteEntity(title,detail)
        notesList.addNote(newNote)
        id = newNote.id
    }


}