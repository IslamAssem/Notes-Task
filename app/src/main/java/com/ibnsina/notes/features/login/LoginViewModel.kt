package com.ibnsina.notes.features.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibnsina.entites.Resource
import com.ibnsina.entites.login.LoginResponse
import com.ibnsina.usecases.usecases.abstraction.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.catch

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {
    val stateLiveData : MutableLiveData<Resource<LoginResponse>> by lazy {
        MutableLiveData<Resource<LoginResponse>> ()
    }
    fun login(email : String, password : String){
        viewModelScope.launch(Dispatchers.IO) {
            loginUseCase.login(email,password)
                .onStart {
                    stateLiveData.postValue(Resource.loading())
                }
                .catch {
                    stateLiveData.postValue(Resource.error(it.message.toString()))
                }
                .collect {
                    stateLiveData.postValue(it)
                }
        }
    }
}