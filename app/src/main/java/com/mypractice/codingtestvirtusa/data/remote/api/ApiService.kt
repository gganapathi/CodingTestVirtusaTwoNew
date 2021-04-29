package com.mypractice.codingtestvirtusa.data.remote.api

import com.mypractice.codingtestvirtusa.data.models.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService{

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Int):Response<UserResponse>
}