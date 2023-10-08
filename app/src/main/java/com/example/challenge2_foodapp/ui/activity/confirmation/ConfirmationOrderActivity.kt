package com.example.challenge2_foodapp.ui.activity.confirmation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge2_foodapp.R
import com.example.challenge2_foodapp.adapter.CartAdapter
import com.example.challenge2_foodapp.adapter.CartConfirmationAdapter
import com.example.challenge2_foodapp.data.repository.CartViewModelFactory
import com.example.challenge2_foodapp.databinding.ActivityConfirmationOrderBinding
import com.example.challenge2_foodapp.ui.fragment.cart.CartViewModel
import com.example.challenge2_foodapp.utils.toCurrencyFormat

class ConfirmationOrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfirmationOrderBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CartConfirmationAdapter
    private var totalPrice = 0

    private val confirmationOrderViewModel: ConfirmationOrderViewModel by lazy {
        ViewModelProvider(
            this,
            CartViewModelFactory(this)
        )[ConfirmationOrderViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmationOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.cartRecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        onClick()

        confirmationOrderViewModel.cart.observe(this) {
            totalPrice =
                it.sumOf { (it.foodItem.price.replace(".0", "").toInt()) * it.foodQuantity }
            binding.totalPayment.text = totalPrice.toCurrencyFormat()

            adapter = CartConfirmationAdapter(it, this)
            recyclerView.adapter = adapter
        }

    }

    private fun onClick() {
        binding.backButton.setOnClickListener {
            onBackPressed()
        }
        binding.orderButton.setOnClickListener {
            Toast.makeText(this, "Order was successfully", Toast.LENGTH_SHORT).show()
        }
    }
}