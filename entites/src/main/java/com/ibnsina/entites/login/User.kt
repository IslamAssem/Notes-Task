package com.ibnsina.entites.login

data class User(val id : Int,
val name : String,
val email : String,
val authenticationToken : String
) {
}