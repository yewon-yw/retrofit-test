package com.example.retrofittest

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface DiaryApi {
    @GET("/diarys")
    fun getDiaryList(
        @Query("select_date") selectDate: String
    ): Call<DiaryList>
}