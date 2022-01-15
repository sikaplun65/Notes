package com.sikaplun.gb.kotlin.notes.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.sikaplun.gb.kotlin.notes.domain.model.NoteEntity

class NotesDiffCallback(
    private val oldNotesList: List<NoteEntity>,
    private val newNotesList: List<NoteEntity>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldNotesList.size
    override fun getNewListSize(): Int = newNotesList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldNote = oldNotesList[oldItemPosition]
        val newNote = newNotesList[newItemPosition]

        return oldNote.id == newNote.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldNote = oldNotesList[oldItemPosition]
        val newNote = newNotesList[newItemPosition]

        return isNotesEqual(oldNote, newNote)
    }

    private fun isNotesEqual(oldNote: NoteEntity, newNote: NoteEntity): Boolean {
        return oldNote.id == newNote.id
                && oldNote.title == newNote.title
                && oldNote.detail == newNote.detail
                && oldNote.createDate == newNote.createDate
                && oldNote.modifiedDate == newNote.modifiedDate
    }
}