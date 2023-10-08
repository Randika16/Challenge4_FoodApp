package com.example.challenge2_foodapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity("food_entity")
data class FoodEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "food_id")
    val id: Int,

    @ColumnInfo(name = "food_name")
    val name: String,

    @ColumnInfo(name = "food_price")
    val price: String,

    @ColumnInfo(name = "food_description")
    val description: String,

    @ColumnInfo(name = "food_location")
    val location: String,

    @ColumnInfo(name = "food_location_link")
    val locationLink: String,

    @ColumnInfo(name = "food_image")
    val image: Int,
) : Serializable