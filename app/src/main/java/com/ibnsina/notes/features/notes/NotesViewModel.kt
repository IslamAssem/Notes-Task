package com.ibnsina.notes.features.notes

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import android.text.format.Time
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibnsina.entites.Resource
import com.ibnsina.entites.note.Note
import com.ibnsina.usecases.usecases.abstraction.NotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val notesUseCase: NotesUseCase) : ViewModel() {
    val notesLiveData by lazy {
        MutableLiveData<Resource<List<Note>>>()
    }
    val saveStatusLiveData by lazy {
        MutableLiveData<Resource<Long>>()
    }
    fun getNotes(){
        viewModelScope.launch(Dispatchers.IO) {
            notesUseCase.getNotes()
                .onStart {
                    notesLiveData.postValue(Resource.loading())
                }
                .catch {
                    notesLiveData.postValue(Resource.error(it.message.toString()))
                }
                .collect {
                    notesLiveData.postValue(Resource.success(it))
                }
        }
    }
    @SuppressLint("SimpleDateFormat")
    fun saveNote(id : Int = 0,title:String, body:String){
        val dateTime = SimpleDateFormat().format(Date(System.currentTimeMillis()))
        saveNote(Note(id,title,body,dateTime,dateTime))
    }
    @SuppressLint("SimpleDateFormat")
    fun saveNote(id : Int = 0,title:String, body:String, date:String){
        val dateTime = SimpleDateFormat().format(Date(System.currentTimeMillis()))
        saveNote(Note(id,title,body,dateTime,date))
    }
    private fun saveNote(note : Note){
        viewModelScope.launch(Dispatchers.IO) {
            notesUseCase.saveNote(note)
                .onStart {
                    saveStatusLiveData.postValue(Resource.loading())
                }
                .catch {
                    saveStatusLiveData.postValue(Resource.error(it.message.toString()))
                }
                .collect {
                    saveStatusLiveData.postValue(Resource.success(it))
                }
        }
    }
}