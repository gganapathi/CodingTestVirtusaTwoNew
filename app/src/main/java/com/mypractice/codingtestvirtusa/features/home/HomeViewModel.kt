package com.mypractice.codingtestvirtusa.features.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mypractice.codingtestvirtusa.data.models.UserResponse
import com.mypractice.codingtestvirtusa.data.remote.datasource.MainRepository
import com.mypractice.codingtestvirtusa.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mainRepository: MainRepository
):ViewModel(){

    private val _res = MutableLiveData<Resource<UserResponse>>()
    private val _userIds = mutableListOf<Int>()
    var _responseEmails: String = ""

    val res : LiveData<Resource<UserResponse>>
        get() = _res

    init {
        _userIds.add(1)
        _userIds.add(3)
        _userIds.add(10)
    }

    fun getUserData() {
        _responseEmails = ""
        CoroutineScope(Dispatchers.IO + Job() ).launch {
            val job = ArrayList<Job>()

            for (i in 0..2) {
                job.add(launch {
                    var id = _userIds[i];
                    mainRepository.getUserById(_userIds[i]).let {
                        if (it.isSuccessful){
                            it?.body()?.data?.email?.let {
                                    it1 -> Log.d("getUserData", it1)
                                _responseEmails = "$_responseEmails\n$it1(id:$id)"
                            }
                            _res.postValue(Resource.success(it.body()))
                        }else{
                            _res.postValue(Resource.error(it.errorBody().toString(), null))
                        }
                    }
                })
            }

            job.joinAll()
        }
    }

}