package com.ibnsina.usecases.usecases.abstraction

import com.ibnsina.entites.note.Note
import kotlinx.coroutines.flow.Flow

interface NotesUseCase {


    suspend fun getNotes():Flow<List<Note>>

    suspend fun saveNote(note: Note): Flow<Long>

}