package com.example.challenge2_foodapp.data.repository

import com.example.challenge2_foodapp.data.FoodDataSource

class FoodRepository(
    private val dataSource: FoodDataSource
) {

    fun getAllFood() = dataSource.getAllFood()

    fun getSpecifiedFood(foodId: Int) = dataSource.getSpecificFood(foodId)

}