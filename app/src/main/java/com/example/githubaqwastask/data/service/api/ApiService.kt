package com.example.githubaqwastask.data.service.api

import com.example.githubaqwastask.data.service.response.Item
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("repositories?language=en")
    suspend fun getItems(): Response<List<Item>>

}