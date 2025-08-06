package com.example.notesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.notesapp.model.NoteDB
import com.example.notesapp.model.NoteEntity
import com.example.notesapp.repo.NoteRepository

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    val allNotes: LiveData<List<NoteEntity>>

    init {
        val dao= NoteDB.getDatabase(application).getNoteDao()
        val repository= NoteRepository(dao)
        allNotes= repository.allNotes

    }
}