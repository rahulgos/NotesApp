package com.example.notesapp.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    // Insert a new note. If the same note already exists (based on primary key), just ignore it.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(noteEntity: NoteEntity)

    // Delete a specific note from the database
    @Delete
    suspend fun delete(noteEntity: NoteEntity)

    @Update
    suspend fun update(note: NoteEntity)

    // Fetch all notes in reverse order (latest note comes first)
    @Query("SELECT * FROM notes_app_table ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<NoteEntity>>
}
