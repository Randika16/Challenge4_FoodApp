package com.example.challenge2_foodapp.ui.activity.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.challenge2_foodapp.R
import com.example.challenge2_foodapp.data.entity.CartEntity
import com.example.challenge2_foodapp.data.entity.FoodEntity
import com.example.challenge2_foodapp.data.repository.CartViewModelFactory
import com.example.challenge2_foodapp.databinding.ActivityDetailBinding
import com.example.challenge2_foodapp.ui.fragment.cart.CartViewModel
import com.example.challenge2_foodapp.utils.toCurrencyFormat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var priceTotal = 0
    private var foodQuantity = 1

    private val cartViewModel: CartViewModel by lazy {
        ViewModelProvider(
            this,
            CartViewModelFactory(this)
        )[CartViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val food = intent.getSerializableExtra("food") as FoodEntity

        binding.lokasiValue.setOnClickListener {
            val foodLocationLink = food.locationLink
            navigateToMaps(foodLocationLink)
        }

        binding.apply {
            val foodName = food.name
            val foodPrice = food.price.replace(".0", "").toInt().toCurrencyFormat()
            val foodImage = food.image
            val foodDescription = food.description
            val foodLocation = food.location

            tvMakananDetail.text = foodName
            ivMakananDetail.setImageResource(foodImage)
            tvHargaDetail.text = foodPrice
            tvDesc.text = foodDescription
            lokasiValue.text = foodLocation
            addToCart.text = getString(R.string.tambah_keranjang, foodPrice)
        }

        binding.ibMinus.setOnClickListener {
            if (foodQuantity > 1) {
                foodQuantity--
                priceTotal = foodQuantity * food.price.replace(".0", "").toInt()
                binding.tvJumlah.text = foodQuantity.toString()
                binding.addToCart.text = getString(R.string.tambah_keranjang, priceTotal.toCurrencyFormat())

            } else {
                Toast.makeText(this, "Cannot be less than one", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.ibPlus.setOnClickListener {
            foodQuantity++
            priceTotal = foodQuantity * food.price.replace(".0", "").toInt()
            binding.tvJumlah.text = foodQuantity.toString()
            binding.addToCart.text = getString(R.string.tambah_keranjang, priceTotal.toCurrencyFormat())
        }

        binding.addToCart.setOnClickListener {
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    cartViewModel.insertIntoCart(
                        CartEntity(
                            foodItem = FoodEntity(
                                food.id,
                                food.name,
                                food.price,
                                food.description,
                                food.location,
                                food.locationLink,
                                food.image
                            ),
                            foodQuantity = foodQuantity,
                            foodNote = null
                        )
                    )
                }
                Toast.makeText(
                    this@DetailActivity,
                    "Successfully added your food to cart",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }

        binding.backButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun navigateToMaps(data: String) {

        // Membuat Intent untuk membuka Google Maps
        val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(data))

        mapIntent.setPackage("com.google.android.apps.maps")

        // Memeriksa apakah Google Maps terinstal
        if (mapIntent.resolveActivity(this.packageManager) != null) {
            // Jika terinstal, buka Google Maps
            startActivity(mapIntent)
        } else {
            // Jika tidak terinstal, Anda dapat menangani kasus ini di sini, misalnya dengan menampilkan pesan kesalahan
            Toast.makeText(
                this,
                "Google Maps is not installed, please install it first",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}