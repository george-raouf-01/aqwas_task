package com.example.githubaqwastask.domain.use_cases

import com.example.githubaqwastask.domain.entities.ItemModel
import com.example.githubaqwastask.domain.repositories.ItemRepository
import com.example.githubaqwastask.presentation.ItemViewModel
import javax.inject.Inject

class SortItemsUseCase @Inject constructor(val itemRepository: ItemRepository) {

    operator fun invoke(
        items: List<ItemModel>?,
        filter: Int?
    ): List<ItemModel>? {
        if (filter == ItemViewModel.SortOption.NAME) {
            return items?.sortedBy {
                it.name
            }

        }
        if (filter == ItemViewModel.SortOption.STARS) {
            return items?.sortedByDescending {  it.stars }

        }
        return items
    }

}