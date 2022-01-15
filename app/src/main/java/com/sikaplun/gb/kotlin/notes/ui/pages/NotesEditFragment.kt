package com.sikaplun.gb.kotlin.notes.ui.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sikaplun.gb.kotlin.notes.R
import com.sikaplun.gb.kotlin.notes.app.App
import com.sikaplun.gb.kotlin.notes.databinding.FragmentNotesEditBinding
import com.sikaplun.gb.kotlin.notes.domain.model.NoteEntity
import com.sikaplun.gb.kotlin.notes.room.NoteListRoomImpl
import com.sikaplun.gb.kotlin.notes.room.NotesListRoom

class NotesEditFragment : Fragment() {
    private lateinit var titleEditText: EditText
    private lateinit var detailEditText: EditText
    private lateinit var locationOfNoteCreation: String
    private lateinit var saveButton: Button
    private val noteDb = App.getInstance().getNotesDb()
    private var notesList: NotesListRoom = NoteListRoomImpl(noteDb.noteDao())

    private var noteId: String = ""
    private var noteTitle: String = ""
    private var noteDetail: String = ""

    private var _binding: FragmentNotesEditBinding? = null
    private val binding get() = _binding!!

    private lateinit var notesEditFragmentViewModel: NotesEditFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesEditBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        titleEditText = binding.titleEditText
        detailEditText = binding.detailEditText
        saveButton = binding.saveButton

        val args = this.arguments
        if (args != null && args.containsKey(ID_KEY)) {
            noteId = args.getString(ID_KEY).toString()
            notesList.getNote(noteId).let { fillTextTitleAndTextDetail(it) }
        }

        notesEditFragmentViewModel =
            ViewModelProvider(this).get(
                NotesEditFragmentViewModel::class.java
            )

        setupListener()
        view.setOnLongClickListener { true }

    }



    private fun fillTextTitleAndTextDetail(note: NoteEntity) {
        titleEditText.setText(note.title)
        detailEditText.setText(note.detail)
        noteTitle = note.title
        noteDetail = note.detail
    }

    private fun setupListener() {

        saveButton.setOnClickListener { v: View? ->
            notesEditFragmentViewModel.onClickSaveButton(
                titleEditText.text.toString(),
                detailEditText.text.toString(),
                noteTitle,
                noteDetail,
                noteId
            )
            requireActivity().onBackPressed()
        }
    }

    companion object {
        val ID_KEY = "ID_KEY"
        fun create(id: String?): NotesEditFragment {
            val fragment = NotesEditFragment()
            val bundle = Bundle().apply { putString(ID_KEY, id) }
            bundle.also { fragment.arguments = it }
            return fragment
        }

        fun create(): NotesEditFragment {
            return NotesEditFragment()
        }
    }
}

