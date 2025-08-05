package com.example.notesapp.repo

import androidx.lifecycle.LiveData
import com.example.notesapp.model.NoteDao
import com.example.notesapp.model.NoteEntity

class NoteRepository (private val noteDao: NoteDao) {

    val allNotes: LiveData<List<NoteEntity>> = noteDao.getAllNotes()

    suspend fun insertNotes(noteEntity: NoteEntity){
        noteDao.insert(noteEntity)
    }

    suspend fun deleteNote(noteEntity: NoteEntity){
        noteDao.delete(noteEntity)
    }


}