package com.ibnsina.usecases.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ibnsina.entites.note.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

@Dao
interface NotesDao {
    @Query("SELECT * FROM note")
    fun getAllNotes(): Flow<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNote(note:Note) : Long

// used to prevent notification of single note when unrelated note changed as room notify on table not row level
//    fun getNoteUntilChanged(id: Int) =
//        getNote(id).distinctUntilChanged()
}