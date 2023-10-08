package com.example.challenge2_foodapp.di

import android.content.Context
import com.example.challenge2_FoodEntityapp.data.room.AppDatabase
import com.example.challenge2_foodapp.data.FoodDataSource
import com.example.challenge2_foodapp.data.FoodDatabaseDataSource
import com.example.challenge2_foodapp.data.repository.FoodRepository

object FoodInjector {
    fun provideRepository(context: Context): FoodRepository {
        val database = AppDatabase.getDatabase(context)
        val foodDao = database.FoodDao()
        val foodDataSource: FoodDataSource = FoodDatabaseDataSource(foodDao)
        return FoodRepository(foodDataSource)
    }
}