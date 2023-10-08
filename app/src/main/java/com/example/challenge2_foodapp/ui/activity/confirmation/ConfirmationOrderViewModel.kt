package com.example.challenge2_foodapp.ui.activity.confirmation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.challenge2_foodapp.data.entity.CartEntity
import com.example.challenge2_foodapp.data.entity.FoodEntity
import com.example.challenge2_foodapp.data.repository.CartRepository
import kotlinx.coroutines.flow.map

class ConfirmationOrderViewModel(private val cartRepository: CartRepository) : ViewModel() {

    val cart: LiveData<List<CartEntity>>
        get() = cartRepository.getAllCartItem().map {
            it.map { cartEntity ->
                CartEntity(
                    cartEntity.id,
                    FoodEntity(
                        cartEntity.foodItem.id,
                        cartEntity.foodItem.name,
                        cartEntity.foodItem.price,
                        cartEntity.foodItem.description,
                        cartEntity.foodItem.location,
                        cartEntity.foodItem.locationLink,
                        cartEntity.foodItem.image
                    ),
                    cartEntity.foodQuantity,
                    cartEntity.foodNote
                )
            }
        }.asLiveData()

}