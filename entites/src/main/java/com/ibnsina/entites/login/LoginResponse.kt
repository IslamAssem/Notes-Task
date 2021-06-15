package com.ibnsina.entites.login
// status = 1 login success
// status = 2 email doesn't exist
// status = 3 wrong password
data class LoginResponse(private val status : Int,
                         val user: User?)
{

    val loginStatus: LoginStatus
    get() = when(status){
        2 -> LoginStatus.EMAIL_ERROR
        3 -> LoginStatus.PASSWORD_ERROR
        else -> LoginStatus.LOGIN_SUCCESS
    }
}
/*
* use as extension variable but status needs to be public
* val LoginResponse.loginStatus: LoginStatus
    get() = when(this.status){
        2 -> LoginStatus.EMAIL_ERROR
        3 -> LoginStatus.PASSWORD_ERROR
        else -> LoginStatus.LOGIN_SUCCESS
    }
* */