package com.example.githubaqwastask.domain.use_cases

import androidx.lifecycle.LiveData
import com.example.githubaqwastask.domain.entities.ItemModel
import com.example.githubaqwastask.domain.repositories.ItemRepository
import com.example.githubaqwastask.utils.Resource
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(val itemRepository: ItemRepository)  {

    operator  fun invoke(): LiveData<Resource<List<ItemModel>>> = itemRepository.getItems()
}