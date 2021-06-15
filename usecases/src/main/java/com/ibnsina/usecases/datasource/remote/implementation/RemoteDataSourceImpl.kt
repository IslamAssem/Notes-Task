package com.ibnsina.usecases.datasource.remote.implementation

import com.ibnsina.entites.Resource
import com.ibnsina.entites.login.LoginResponse
import com.ibnsina.usecases.datasource.remote.abstraction.RemoteDataSource
import com.ibnsina.usecases.datasource.remote.gateways.ServerGateway
import com.ibnsina.usecases.datasource.remote.gateways.getResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor (val serverGateway: ServerGateway) : RemoteDataSource{
    override suspend fun login(
        email: String,
        password: String
    ): Flow<Resource<LoginResponse>> = flow {
        emit(getResult() {  (serverGateway.login(email,password)) } )
    }
}