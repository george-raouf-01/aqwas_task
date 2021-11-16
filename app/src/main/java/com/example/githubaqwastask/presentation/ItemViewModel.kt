package com.example.githubaqwastask.presentation

import androidx.lifecycle.*
import com.example.githubaqwastask.domain.entities.ItemModel
import com.example.githubaqwastask.domain.use_cases.SortItemsUseCase
import com.example.githubaqwastask.domain.use_cases.GetItemsUseCase
import com.example.githubaqwastask.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val sortItemsUseCase: SortItemsUseCase,
    private val getItemsUseCase: GetItemsUseCase
) : ViewModel() {

    private fun getItems() = getItemsUseCase.invoke()

    val itemsLD = MutableLiveData<Resource<List<ItemModel>>>()

    private fun filterItems(
        items: List<ItemModel>?,
        filter: Int?
    ) {
        itemsLD.value = Resource.Success(sortItemsUseCase.invoke(items, filter))
    }


    fun handleItems() {
        viewModelScope.launch {
            getItems().asFlow().collect {
                itemsLD.value = it
            }
        }
    }

    fun handleVisibility() {
        val res = itemsLD.value!!.data
        itemsLD.value = Resource.Success(
            res.apply {
                this?.firstOrNull {
                    it.isCurrentInFocus == true
                }?.apply {
                    isCurrentInFocus = false
                }
            }
        )
    }

    object SortOption {
        const val NAME = 1
        const val STARS = 2
    }

    fun sortBy(sortType: Int) {
        filterItems(itemsLD.value?.data, sortType)
    }

}