package com.example.challenge2_foodapp.data.room

import androidx.room.Dao
import androidx.room.Query
import com.example.challenge2_foodapp.data.entity.FoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {

    @Query("SELECT * FROM food_entity")
    fun getAllFood(): Flow<List<FoodEntity>>

    @Query("SELECT * FROM food_entity WHERE food_id = :foodId")
    fun getSpecificFood(foodId: Int): Flow<FoodEntity>

}