package com.ibnsina.usecases.repositories.implementation

import com.ibnsina.entites.note.Note
import com.ibnsina.usecases.datasource.abstraction.NotesDataSource
import com.ibnsina.usecases.datasource.remote.abstraction.RemoteDataSource
import com.ibnsina.usecases.repositories.abstraction.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor (private val dataSource: RemoteDataSource,
                                          private val notesDataSource: NotesDataSource): Repository {
    override suspend fun login(
        email: String,
        password: String
    ) = dataSource.login(email,password)


    override suspend fun getAllNotes(): Flow<List<Note>> = notesDataSource.getAllNotes()
    override suspend fun saveNote(note: Note) = notesDataSource.saveNote(note)
}