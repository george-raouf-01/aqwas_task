package com.example.githubaqwastask.data.mapper

import com.example.githubaqwastask.data.mapper.base.BaseMapper
import com.example.githubaqwastask.data.service.response.Item
import com.example.githubaqwastask.domain.entities.ItemModel
import javax.inject.Inject

class ItemMapper @Inject constructor(): BaseMapper<List<Item>, List<ItemModel>> {

    override fun transform(type: List<Item>?): List<ItemModel> {
        val itemModelList = mutableListOf<ItemModel>()
        type?.forEach {
            itemModelList.add(
                ItemModel(
                    name = it.name,
                    author = it.author,
                    avatar = it.avatar,
                    description = it.description,
                    forks = it.forks,
                    language = it.language,
                    stars = it.stars
                )
            )
        }
        return itemModelList
    }
}