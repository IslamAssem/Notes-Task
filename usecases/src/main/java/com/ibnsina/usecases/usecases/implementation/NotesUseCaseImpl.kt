package com.ibnsina.usecases.usecases.implementation

import com.ibnsina.entites.note.Note
import com.ibnsina.usecases.repositories.abstraction.Repository
import com.ibnsina.usecases.usecases.abstraction.NotesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesUseCaseImpl @Inject constructor(private val repository: Repository) : NotesUseCase {
    override suspend fun getNotes(): Flow<List<Note>> = repository.getAllNotes()
    override suspend fun saveNote(note: Note) = repository.saveNote(note)
}