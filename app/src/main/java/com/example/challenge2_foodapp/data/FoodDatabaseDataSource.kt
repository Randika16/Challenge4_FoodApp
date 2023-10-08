package com.example.challenge2_foodapp.data

import com.example.challenge2_foodapp.data.entity.FoodEntity
import com.example.challenge2_foodapp.data.room.FoodDao
import kotlinx.coroutines.flow.Flow


interface FoodDataSource {
    fun getAllFood(): Flow<List<FoodEntity>>
    fun getSpecificFood(foodId: Int): Flow<FoodEntity>
}

class FoodDatabaseDataSource(private val foodDao: FoodDao) : FoodDataSource {

    override fun getAllFood(): Flow<List<FoodEntity>> {
        return foodDao.getAllFood()
    }

    override fun getSpecificFood(foodId: Int): Flow<FoodEntity> {
        return foodDao.getSpecificFood(foodId)
    }

}