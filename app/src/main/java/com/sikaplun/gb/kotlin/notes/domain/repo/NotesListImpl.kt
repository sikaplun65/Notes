package com.sikaplun.gb.kotlin.notes.domain.repo

import android.os.Parcelable
import com.sikaplun.gb.kotlin.notes.domain.model.NoteEntity
import com.sikaplun.gb.kotlin.notes.domain.repository.Noteslist
import kotlinx.parcelize.Parcelize

@Parcelize
object NotesListImpl : Noteslist, Parcelable {

    private val notesList: MutableList<NoteEntity> = mutableListOf()
    private var foundNoteslist: MutableList<NoteEntity> = mutableListOf()

    init {
        filListOfNotesWithTestValues()
    }

    fun getNotesList(): NotesListImpl {
        return NotesListImpl
    }

    override fun getNotes(): List<NoteEntity> = notesList

    override fun getFoundNotesList(): List<NoteEntity> = foundNoteslist

    override fun getNote(id: String?): NoteEntity? = notesList.find { it.id.equals(id) }

    override fun addNote(note: NoteEntity?) {
        if (note != null) {
            notesList.add(note)
        }
    }

    override fun removeNote(note: NoteEntity?) {
        notesList.remove(note)
    }

    override fun sortFromOldToNewNotes() = notesList.sortBy { it.createDate }

    override fun sortFromNewToOldNotes() = notesList.sortByDescending { it.createDate }

    override fun sorByDateModifiedNotes() =
        notesList.sortWith(compareBy(nullsLast(reverseOrder()), { it.modifiedDate }))

    override fun searchNotes(str: String) {
        foundNoteslist.clear()

        foundNoteslist = notesList.filter { note ->
            note.detail.contains(str, ignoreCase = true)
                    || note.title.contains(str, ignoreCase = true)
        } as MutableList<NoteEntity>
    }

    private fun filListOfNotesWithTestValues() {
        val numberOfNotes = 6
        for (i in 0 until numberOfNotes) {
            try {
                // задержка времени при создании для проверки сортировок
                Thread.sleep(10)
                notesList.add(
                    NoteEntity(
                        "Заметка " + (i + 1),
                        "Сайт https://fishtext.ru поможет дизайнеру, верстальщику," +
                                " вебмастеру сгенерировать несколько абзацев более менее осмысленного текста"
                    )
                )
            } catch (ex: InterruptedException) {
                Thread.currentThread().interrupt()
            }
        }
    }

}