package com.ibnsina.usecases.datasource.remote.abstraction

import com.ibnsina.entites.Resource
import com.ibnsina.entites.login.LoginResponse
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun login(email:String,password:String) : Flow<Resource<LoginResponse>>

}