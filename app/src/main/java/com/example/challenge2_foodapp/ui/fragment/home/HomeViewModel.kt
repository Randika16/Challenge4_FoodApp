package com.example.challenge2_foodapp.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.challenge2_foodapp.data.entity.FoodEntity
import com.example.challenge2_foodapp.data.repository.FoodRepository
import kotlinx.coroutines.flow.map

class HomeViewModel(private val foodRepository: FoodRepository) : ViewModel() {

    val food: LiveData<List<FoodEntity>>
        get() = foodRepository.getAllFood().map {
            it.map { foodEntity ->
                FoodEntity(
                    foodEntity.id,
                    foodEntity.name,
                    foodEntity.price,
                    foodEntity.description,
                    foodEntity.location,
                    foodEntity.locationLink,
                    foodEntity.image
                )
            }
        }.asLiveData()

    fun getSpecifiedFood(foodId: Int) = foodRepository.getSpecifiedFood(foodId)

}