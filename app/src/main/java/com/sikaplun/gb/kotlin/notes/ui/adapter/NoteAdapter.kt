package com.sikaplun.gb.kotlin.notes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sikaplun.gb.kotlin.notes.databinding.ItemNoteBinding
import com.sikaplun.gb.kotlin.notes.domain.model.NoteEntity

class NoteAdapter(
    private var list: List<NoteEntity>,
    private var clickListener: InteractionListener
) : RecyclerView.Adapter<NoteViewHolder>() {


    fun setData(list: List<NoteEntity>) {
        val diffCallback = NotesDiffCallback(this.list, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.list = list
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun getItem(position: Int): NoteEntity {
        return list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setOnItemClickListener(listener: InteractionListener) {
        clickListener = listener
    }

    interface InteractionListener {
        fun OnItemShortClick(item: NoteEntity?)
        fun OnItemLongClick(item: NoteEntity?): Boolean
    }
}