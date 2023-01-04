package com.example.retrofittest

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface UserApi {
    @GET("/users")
    fun getUser(): Call<User>
}