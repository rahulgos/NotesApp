package com.example.notesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notesapp.model.NoteDB
import com.example.notesapp.model.NoteEntity
import com.example.notesapp.repo.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// ViewModel acts as a bridge between UI and Repository.
// Helps keep the UI code clean and survives configuration changes.
class NoteViewModel(application: Application) : AndroidViewModel(application) {

    val allNotes: LiveData<List<NoteEntity>>
    private val repository: NoteRepository

    init {
        // Initialize DAO and Repository using the application context
        val dao = NoteDB.getDatabase(application).getNoteDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    // Called to insert a new note using Coroutine on background thread
    fun insertNote(noteEntity: NoteEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertNote(noteEntity)
    }

    // Called to delete a note using Coroutine on background thread
    fun deleteNote(noteEntity: NoteEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteNote(noteEntity)
    }
}
