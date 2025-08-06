package com.example.notesapp.model

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Define the Room database and list all entities (in our case, just NoteEntity)
@Database([NoteEntity::class], version = 1, exportSchema = false)
abstract class NoteDB : RoomDatabase() {

    // DAO for accessing note-related database operations
    abstract fun getNoteDao(): NoteDao

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: NoteDB? = null

        // Singleton pattern to avoid multiple instances of the database
        fun getDatabase(context: Context): NoteDB {
            return INSTANCE ?: synchronized(this) {
                // Build the Room database instance if it's not already created
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDB::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
