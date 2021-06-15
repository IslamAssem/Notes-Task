package com.ibnsina.usecases.usecases.abstraction

import com.ibnsina.entites.Resource
import com.ibnsina.entites.login.LoginResponse
import kotlinx.coroutines.flow.Flow

interface LoginUseCase {
    suspend fun login(email:String,password:String) : Flow<Resource<LoginResponse>>

}