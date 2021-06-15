package com.ibnsina.usecases.datasource.implementation

import com.ibnsina.entites.note.Note
import com.ibnsina.usecases.datasource.abstraction.NotesDataSource
import com.ibnsina.usecases.datasource.database.NotesDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NotesDataSourceImpl @Inject constructor(private val dao: NotesDao) : NotesDataSource {
    override suspend fun getAllNotes(): Flow<List<Note>> = dao.getAllNotes()
    override suspend fun saveNote(note: Note) = flow {
        emit(dao.saveNote(note))
    }

}