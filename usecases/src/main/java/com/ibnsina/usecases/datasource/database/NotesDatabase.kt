package com.ibnsina.usecases.datasource.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ibnsina.entites.note.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {

    abstract val notesDao: NotesDao

    companion object {

        @Volatile
        private lateinit var INSTANCE: NotesDatabase
        private const val DATABASE_NAME = "notes_database"

        fun getInstance(context: Application): NotesDatabase {
            synchronized(this) {
                if(!::INSTANCE.isInitialized)
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        NotesDatabase::class.java,
                        DATABASE_NAME
                    ).fallbackToDestructiveMigration().build()
                return INSTANCE
            }
        }
    }
}