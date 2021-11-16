package com.example.githubaqwastask.domain.repositories

import androidx.lifecycle.LiveData
import com.example.githubaqwastask.domain.entities.ItemModel
import com.example.githubaqwastask.utils.Resource

interface ItemRepository {
    fun getItems(): LiveData<Resource<List<ItemModel>>>
}