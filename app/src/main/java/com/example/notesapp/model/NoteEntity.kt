package com.example.notesapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_app_table")
class NoteEntity (@ColumnInfo ("text") val text: String) {
    @PrimaryKey (autoGenerate = true) var id= 0


}