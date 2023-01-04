package com.example.retrofittest

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("isSuccess")
    val isSuccess: Boolean,
    @SerializedName("code")
    val code: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("result")
    val result: UserResult
)

data class UserResult(
    @SerializedName("user_email")
    val userEmail: String,
    @SerializedName("user_nickname")
    val userNickname: String,
    @SerializedName("user_profile_img")
    val userProfileImg: String
)
