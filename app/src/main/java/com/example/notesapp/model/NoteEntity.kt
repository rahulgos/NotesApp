package com.example.notesapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Represents a single note in our Room database
@Entity(tableName = "notes_app_table")
class NoteEntity(

    // The actual content of the note
    @ColumnInfo(name = "text")
    val text: String

) {
    // Auto-generated unique ID for each note (primary key)
    @PrimaryKey(autoGenerate = true)
    var id = 0
}
