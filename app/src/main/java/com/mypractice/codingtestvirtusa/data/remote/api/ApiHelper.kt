package com.mypractice.codingtestvirtusa.data.remote.api

import com.mypractice.codingtestvirtusa.data.models.UserResponse
import retrofit2.Response

interface ApiHelper {

    suspend fun getUserById(id : Int):Response<UserResponse>

}