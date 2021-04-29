package com.mypractice.codingtestvirtusa.data.remote.datasource

import com.mypractice.codingtestvirtusa.data.remote.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiHelper: ApiHelper
){
    suspend fun getUserById(id: Int) = apiHelper.getUserById(id)
}