package com.example.githubaqwastask.data.repositories

import com.example.githubaqwastask.data.mapper.ItemMapper
import com.example.githubaqwastask.data.service.api.ApiService
import com.example.githubaqwastask.data.service.response.Item
import com.example.githubaqwastask.di.ApplicationScope
import com.example.githubaqwastask.domain.entities.ItemModel
import com.example.githubaqwastask.domain.repositories.ItemRepository
import com.example.githubaqwastask.utils.CleanNetworkBoundResource
import kotlinx.coroutines.CoroutineScope
import retrofit2.Response
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    @ApplicationScope private val applicationScope: CoroutineScope,
    val mapper: ItemMapper
) : ItemRepository {
    override fun getItems() =
        object : CleanNetworkBoundResource<List<Item>, List<ItemModel>>(applicationScope, mapper) {
            override suspend fun createCall(): Response<List<Item>> = apiService.getItems()

        }.asLiveData()

}