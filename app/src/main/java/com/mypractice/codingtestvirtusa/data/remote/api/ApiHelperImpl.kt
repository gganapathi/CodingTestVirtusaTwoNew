package com.mypractice.codingtestvirtusa.data.remote.api

import com.mypractice.codingtestvirtusa.data.models.UserResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
):ApiHelper{

    override suspend fun getUserById(id: Int): Response<UserResponse> = apiService.getUserById(id)

}