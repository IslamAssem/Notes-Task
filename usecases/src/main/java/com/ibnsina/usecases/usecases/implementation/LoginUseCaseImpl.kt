package com.ibnsina.usecases.usecases.implementation

import com.ibnsina.usecases.repositories.abstraction.Repository
import com.ibnsina.usecases.usecases.abstraction.LoginUseCase
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor (private val repository: Repository) : LoginUseCase {
    override suspend fun login(
        email: String,
        password: String
    ) = repository.login(email,password)
}