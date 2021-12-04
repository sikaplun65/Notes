package com.sikaplun.gb.kotlin.notes.ui.pages

import androidx.lifecycle.ViewModel
import com.sikaplun.gb.kotlin.notes.app.App
import com.sikaplun.gb.kotlin.notes.domain.model.NoteEntity
import com.sikaplun.gb.kotlin.notes.room.NoteListRoomImpl
import com.sikaplun.gb.kotlin.notes.room.NotesListRoom
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
            var title = titleEditText
            if(titleEditText.isEmpty()){
                title = createTitleIfEmptyTitle(detailEditText)
            }
            createNote(title, detailEditText)
            isModifiedDate = false
        }

        if (isModifiedDate && tempTitle != titleEditText) {
            notesList.updateNote(id, titleEditText, detailEditText, Calendar.getInstance())
        }

        if (isModifiedDate && tempDetail != detailEditText) {
            notesList.updateNote(id, titleEditText, detailEditText, Calendar.getInstance())
        }

        removeNoteIfEmpty(titleEditText, detailEditText)
    }

    private fun createTitleIfEmptyTitle(detailEditText: String): String {
        return if (detailEditText.length < 10) detailEditText else detailEditText.substring(0, 10)
    }

    private fun removeNoteIfEmpty(titleEditText: String, detailEditText: String) {
        if (titleEditText.isEmpty() && detailEditText.isEmpty()) {
            notesList.removeNote(notesList.getNote(id))
        }
    }

    private fun createNote(title: String, detail: String) {
        val newNote = NoteEntity(title, detail)
        notesList.addNote(newNote)
        id = newNote.id
    }

}