package com.example.notesapp.model

import android.annotation.SuppressLint
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database([NoteEntity::class], version = 1, exportSchema = false)
abstract class NoteDB : RoomDatabase(){

    abstract fun noteDao(): NoteDao

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: NoteDB? = null

        fun getDatabase(context: Context): NoteDB {
            return INSTANCE ?: synchronized(this) {
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
