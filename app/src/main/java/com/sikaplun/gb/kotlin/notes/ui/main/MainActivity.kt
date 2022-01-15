package com.sikaplun.gb.kotlin.notes.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sikaplun.gb.kotlin.notes.R
import com.sikaplun.gb.kotlin.notes.databinding.ActivityMainBinding
import com.sikaplun.gb.kotlin.notes.ui.pages.NotesEditFragment.Companion.create
import com.sikaplun.gb.kotlin.notes.ui.pages.NotesListFragment
import com.sikaplun.gb.kotlin.notes.ui.pages.SortNotesFragment


class MainActivity : AppCompatActivity(), NotesListFragment.Controller,
    SortNotesFragment.SortChangeListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var notesListFragment: NotesListFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
        initNotesListFragment(savedInstanceState)
        initBottomNavigationMenu()

    }


    private fun initNotesListFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            return
        }
        notesListFragment = NotesListFragment()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, notesListFragment, "NOTES_LIST_FRAGMENT")
            .commit()
    }

    private fun addFragment(f: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, f)
            .addToBackStack(null)
            .commit()
    }

    private fun initBottomNavigationMenu() {
        binding.bottomNavView.setOnItemSelectedListener {

            val settingsFragment =
                supportFragmentManager.findFragmentByTag("sort_notes_fragment")
            if (settingsFragment == null) {
                val fragment = SortNotesFragment()
                fragment.callbackSortType = this

                supportFragmentManager
                    .beginTransaction()
                    .add(
                        R.id.fragment_container,
                        fragment,
                        "sort_notes_fragment"
                    )
                    .addToBackStack(null)
                    .commit()
            }
            return@setOnItemSelectedListener true
        }
    }

    override fun startNotesEditFragment(id: String?) {
        addFragment(create(id))
    }

    override fun startNotesCreateFragment() {
        addFragment(create())
    }

    override fun sortChange(type: NotesListFragment.SortType) {
        notesListFragment.sortNotesList(type)
        supportFragmentManager.popBackStack()
    }
}