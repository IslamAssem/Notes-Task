package com.ibnsina.usecases.datasource.abstraction

import com.ibnsina.entites.note.Note
import kotlinx.coroutines.flow.Flow

interface NotesDataSource {
    suspend fun getAllNotes():Flow<List<Note>>

    suspend fun saveNote(note: Note): Flow<Long>
}