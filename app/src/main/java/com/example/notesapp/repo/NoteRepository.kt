package com.example.notesapp.repo

import androidx.lifecycle.LiveData
import com.example.notesapp.model.NoteDao
import com.example.notesapp.model.NoteEntity

// Acts as a single source of truth for data handling
class NoteRepository(private val noteDao: NoteDao) {

    // Expose all notes as LiveData so the UI can observe changes
    val allNotes: LiveData<List<NoteEntity>> = noteDao.getAllNotes()

    // Used to insert a note into the database (runs on background thread)
    suspend fun insertNote(noteEntity: NoteEntity) {
        noteDao.insert(noteEntity)
    }

    // Used to delete a specific note from the database
    suspend fun deleteNote(noteEntity: NoteEntity) {
        noteDao.delete(noteEntity)
    }
}
