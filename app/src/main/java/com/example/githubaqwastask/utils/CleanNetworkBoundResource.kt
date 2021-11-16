package com.example.githubaqwastask.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.githubaqwastask.data.mapper.base.BaseMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Response

abstract class CleanNetworkBoundResource<ResponseType, ResultType>(val applicationScope: CoroutineScope, val mapper:BaseMapper<ResponseType, ResultType>) {

    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        handle()
    }

    fun handle() {
        applicationScope.launch {
            result.postValue(Resource.Loading(null))
            try {
                val resposne = createCall()
                if (resposne.isSuccessful) {
                    resposne.body()?.let {
                        result.postValue(Resource.Success(map(it)))
                    } ?: run {
                        result.postValue(Resource.Success(null))
                    }
                } else {
                    result.postValue(Resource.Error(message = resposne.message() ?: "Error Retrieving Data", null))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                result.postValue(Resource.Error(message = "$e" ?: "Error", null))
            }
        }

    }

    abstract suspend fun createCall(): Response<ResponseType>

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    fun map(result: ResponseType) = mapper.transform(result)

}