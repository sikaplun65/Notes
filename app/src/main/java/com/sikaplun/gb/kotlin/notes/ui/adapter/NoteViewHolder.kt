package com.sikaplun.gb.kotlin.notes.ui.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.sikaplun.gb.kotlin.notes.databinding.ItemNoteBinding
import com.sikaplun.gb.kotlin.notes.domain.model.NoteEntity
import java.text.SimpleDateFormat
import java.util.*

class NoteViewHolder(binding: ItemNoteBinding, clickListener: NoteAdapter.InteractionListener) :
    RecyclerView.ViewHolder(binding.root) {

    private val createDateTextView = binding.createDateTextView
    private val modifiedDateTextView = binding.modifiedDateTextView
    private val titleTextView = binding.titleTextView
    private val detailTextView = binding.detailTextView
    private var note: NoteEntity? = null

    @SuppressLint("SimpleDateFormat")
    fun bind(note: NoteEntity) {
        this.note = note
        modifiedDateTextView.text = getTextModifiedDate(note)
        createDateTextView.text = SimpleDateFormat("dd/MM/yyyy HH:mm").format(note.createDate.time)
        titleTextView.text = note.title
        detailTextView.text = note.detail
    }

    @SuppressLint("SimpleDateFormat")
    private fun getTextModifiedDate(note: NoteEntity): String? {
        return if (note.modifiedDate != null) {
            if (note.modifiedDate!![Calendar.DAY_OF_MONTH] == Calendar.getInstance()[Calendar.DAY_OF_MONTH]
            ) {
                " изменено: сегодня в " + SimpleDateFormat("HH.mm")
                    .format(Calendar.getInstance().time)
            } else " изменено: " + SimpleDateFormat("dd/MM/yyyy  HH.mm")
                .format(note.createDate.time)
        } else null
    }

    init {
        itemView.setOnClickListener { clickListener.OnItemShortClick(note) }
        itemView.setOnLongClickListener { clickListener.OnItemLongClick(note) }
    }

}

