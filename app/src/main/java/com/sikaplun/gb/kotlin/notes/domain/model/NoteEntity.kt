package com.sikaplun.gb.kotlin.notes.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

const val NOTE_TABLE = "notesList"
const val CREATE_DATE = "CREATE_DATE"
const val NOTE_COLUMN_ID = "id"
const val MODIFIED_DATE = "MODIFIED_DATE"
const val NOTE_TITLE = "TITLE"
const val NOTE_DETAIL = "DETAIL"

@Entity(tableName = NOTE_TABLE)
class NoteEntity {
    @ColumnInfo(name = CREATE_DATE)
    var createDate: Calendar

    @ColumnInfo(name = MODIFIED_DATE)
    var modifiedDate: Calendar?= null

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = NOTE_COLUMN_ID)
    var id: String = ""


    @ColumnInfo(name = NOTE_TITLE)
    var title: String

    @ColumnInfo(name = NOTE_DETAIL)
    var detail: String

    constructor() {
        title = ""
        detail = ""
        createDate = Calendar.getInstance()
        generateId()
    }

    constructor(title: String, detail: String) {
        this.title = title
        this.detail = detail
        createDate = Calendar.getInstance()
        generateId()
    }

    private fun generateId() {
        id = Calendar.getInstance().time.time.toString()
    }
}