package com.example.githubaqwastask

import com.example.githubaqwastask.depdencies.mockRepository
import com.example.githubaqwastask.depdencies.mockSortItemsUseCase
import com.example.githubaqwastask.domain.entities.ItemModel
import com.example.githubaqwastask.presentation.ItemViewModel
import com.example.githubaqwastask.utils.Resource
import org.junit.Assert
import org.junit.Test

class ItemViewModelTest {

    var sortItemsUseCase = mockSortItemsUseCase()
    var mockRepository = mockRepository()

    @Test
    fun test_filter_items_by_name_result_success() {

        //create dummy list
        val list = listOf(
            ItemModel(name = "bbb"),
            ItemModel(name = "aaa"),
            ItemModel(name = "ccc"),
        )

        //apply filter list
        val filteredList = sortItemsUseCase.invoke(list, ItemViewModel.SortOption.NAME)

        //assert sorting success
        Assert.assertEquals("aaa", filteredList?.get(0)?.name)
    }

    @Test
    fun test_filter_items_by_name_result_error() {

        //create dummy list
        val list = listOf(
            ItemModel(name = "bbb"),
            ItemModel(name = "aaa"),
            ItemModel(name = "ccc"),
        )

        //apply filter list
        val filteredList = sortItemsUseCase.invoke(list, ItemViewModel.SortOption.NAME)

        //assert sorting success
        Assert.assertEquals("bbb", filteredList?.get(0)?.name)
    }

    @Test
    fun test_filter_items_by_stars_result_success() {

        //create dummy list
        val list = listOf(
            ItemModel(stars = 5),
            ItemModel(stars = 7),
            ItemModel(stars = 9),
        )

        //apply filter list
        val filteredList = sortItemsUseCase.invoke(list, ItemViewModel.SortOption.STARS)

        //assert sorting success
        Assert.assertEquals(9, filteredList?.get(0)?.stars)
    }

    @Test
    fun test_filter_items_by_stars_result_error() {


        //create dummy list
        val list = listOf(
            ItemModel(stars = 5),
            ItemModel(stars = 7),
            ItemModel(stars = 9),
        )

        //apply filter list
        val filteredList = sortItemsUseCase.invoke(list, ItemViewModel.SortOption.STARS)

        //assert sorting success
        Assert.assertEquals(5, filteredList?.get(0)?.stars)
    }
}