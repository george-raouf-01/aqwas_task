package com.example.githubaqwastask.depdencies

import com.example.githubaqwastask.data.service.response.Item
import com.example.githubaqwastask.domain.entities.ItemModel
import com.example.githubaqwastask.domain.repositories.ItemRepository
import com.example.githubaqwastask.utils.CleanNetworkBoundResource
import retrofit2.Response

class MockRepository: ItemRepository {

    private val apiService = mockApi()
    private val applicationScope = mockScope()
    private val mapper = mockMapper()


    override fun getItems()  = object : CleanNetworkBoundResource<List<Item>, List<ItemModel>>(applicationScope, mapper) {
        override suspend fun createCall(): Response<List<Item>> = apiService.getItems()

    }.asLiveData()
}