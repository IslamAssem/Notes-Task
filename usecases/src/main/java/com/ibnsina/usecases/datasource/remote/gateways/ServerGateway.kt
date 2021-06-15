package com.ibnsina.usecases.datasource.remote.gateways

import com.ibnsina.entites.login.LoginResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ServerGateway {
    @FormUrlEncoded
    @POST(ServerConstants.LOGIN)
    suspend fun login(@Field(ServerConstants.EMAIL) email : String,
              @Field(ServerConstants.PASSWORD) password : String) : Response<LoginResponse>
}