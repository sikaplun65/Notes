package com.sikaplun.gb.kotlin.notes.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sikaplun.gb.kotlin.notes.domain.model.NOTE_TABLE
import com.sikaplun.gb.kotlin.notes.domain.model.NoteEntity
import java.util.*

@Dao
interface NoteDao {
    @Insert
    fun addNote(noteEntity: NoteEntity)

    @Query("delete from notesList where id in (:noteId)")
    fun removeNote(noteId: String)

    @Query("update notesList set title =:noteTitle, detail =:noteDetail, MODIFIED_DATE =:date where id =:noteId")
    fun updateNote(noteId: String, noteTitle: String, noteDetail: String, date: Calendar)

    @Query("select*from $NOTE_TABLE")
    fun getNotes(): List<NoteEntity>

    @Query("select * from $NOTE_TABLE  where id =:noteId")
    fun getNote(noteId: String): NoteEntity

    @Query("select*from $NOTE_TABLE order by CREATE_DATE asc")
    fun sortFromOldToNewNotes(): List<NoteEntity>

    @Query("select*from $NOTE_TABLE order by CREATE_DATE desc")
    fun sortFromNewToOldNotes(): List<NoteEntity>

    @Query("select*from $NOTE_TABLE order by MODIFIED_DATE desc")
    fun sorByDateModifiedNotes(): List<NoteEntity>

    @Query("select*from $NOTE_TABLE where title like '%' || :str ||'%' or detail like '%'|| :str ||'%'")
    fun searchNotes(str: String): List<NoteEntity>

}