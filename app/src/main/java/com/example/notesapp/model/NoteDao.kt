package com.example.notesapp.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert (noteEntity: NoteEntity)


    @Delete
    suspend fun delete (noteEntity: NoteEntity)


    @Query("SELECT * FROM notes_app_table ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<NoteEntity>>


}