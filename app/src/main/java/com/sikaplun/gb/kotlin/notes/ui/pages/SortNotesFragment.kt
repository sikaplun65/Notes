package com.sikaplun.gb.kotlin.notes.ui.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sikaplun.gb.kotlin.notes.databinding.FragmentSortNotesBinding


class SortNotesFragment : Fragment() {

    interface SortChangeListener {
        fun sortChange(type:NotesListFragment.SortType)
    }


    private lateinit var _binding: FragmentSortNotesBinding
    private val binding get() = _binding

    var callbackSortType: SortChangeListener? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSortNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.sortFromOldToNewNotesButton.setOnClickListener {
            callbackSortType?.sortChange(NotesListFragment.SortType.OldToNew)
        }
        binding.sortFromNewToOldNotesButton.setOnClickListener {
            callbackSortType?.sortChange(NotesListFragment.SortType.NewToOld)
        }
        binding.sort1ByModifiedDateButton.setOnClickListener {
            callbackSortType?.sortChange(NotesListFragment.SortType.ByModifiedDate)
        }
    }

}