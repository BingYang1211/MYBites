package com.example.mybites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.example.mybites.databinding.ActivityGroceriesBinding

class GroceriesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGroceriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGroceriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load product image using Glide
        Glide.with(this).load(intent.getStringExtra("prodImg")).into(binding.itemImage)

        // Set product name
        binding.CategoryTitle.text = intent.getStringExtra("prodName")

        // Set product price
        val priceString = intent.getStringExtra("price")
        Log.d("Debug", "Price String: $priceString")
        val priceDecimal = priceString?.toDoubleOrNull() ?: 0.0
        Log.d("Debug", "Price Decimal: $priceDecimal")
        binding.price.text = String.format("RM %.2f", priceDecimal)
        Log.d("Debug", "Binding Price Text: ${binding.price.text}")

        // Set product description
        binding.descriptionData.text = intent.getStringExtra("dcrp")


        // Back button click listener
        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}

        // Quantity picker functionality
       //binding.quantityPicker.minValue = 0
       // binding.quantityPicker.maxValue = 50

        //binding.increaseButton.setOnClickListener {
            // Increase button clicked
           // val currentValue = binding.quantityPicker.value
          //  binding.quantityPicker.value = currentValue + 1
       // }

      //  binding.decreaseButton.setOnClickListener {
            // Decrease button clicked
         //   val currentValue = binding.quantityPicker.value
          //  if (currentValue > 0) {
              //  binding.quantityPicker.value = currentValue - 1
           // }
        //}
   // }

    // Function to update the text of the quantity button
  //  fun updateQuantityButtonText(quantity: Int) {
       // binding.addToCart.text = "Add $quantity to Cart"
   // }
//}




