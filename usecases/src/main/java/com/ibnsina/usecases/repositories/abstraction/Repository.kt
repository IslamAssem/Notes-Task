package com.ibnsina.usecases.repositories.abstraction

import com.ibnsina.entites.Resource
import com.ibnsina.entites.login.LoginResponse
import com.ibnsina.entites.note.Note
import kotlinx.coroutines.flow.Flow


interface Repository {
    suspend fun login(email:String,password:String) : Flow<Resource<LoginResponse>>

    suspend fun getAllNotes():Flow<List<Note>>

    suspend fun saveNote(note: Note): Flow<Long>

}