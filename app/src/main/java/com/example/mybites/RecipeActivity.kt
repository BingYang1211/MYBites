package com.example.mybites

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.mybites.databinding.ActivityRecipeBinding

class RecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeBinding
    var imgCrop = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemImage)
        binding.CategoryTitle.text = intent.getStringExtra("tittle")

        binding.stepData.text = intent.getStringExtra("des")
        var ing =
            intent.getStringExtra("ing")?.split("\n".toRegex())?.dropLastWhile { it.isEmpty() }
                ?.toTypedArray()
        binding.time.text = ing?.get(0)

        var ingDataText = ""
        ing?.let {
            for (i in 1 until it.size) {
                ingDataText += if (ingDataText.isEmpty()) {
                    "🟤 ${it[i]}"
                } else {
                    "\n🟤 ${it[i]}"
                }
            }
        }
        binding.ingData.text = ingDataText

        binding.step.background = null
        binding.step.setTextColor(getColor(R.color.black))
        binding.step.setOnClickListener {
            binding.step.setBackgroundResource(R.drawable.btn1)
            binding.step.setTextColor(getColor(R.color.white))
            binding.ing.setTextColor(getColor(R.color.black))
            binding.ing.background = null
            binding.stepScroll.visibility = View.VISIBLE
            binding.ingScroll.visibility = View.GONE

        }

        binding.step.setOnClickListener {
            // Inside the listener, use safe calls (?.) to access views
            binding.step.setBackgroundResource(R.drawable.btn1)
            binding.step.setTextColor(getColor(R.color.white))
            binding.ing.setTextColor(getColor(R.color.black))
            binding.ing.background = null
            binding.stepScroll.visibility = View.VISIBLE
            binding.ingScroll.visibility = View.GONE
        }

        binding.ing.setOnClickListener {
            // Inside the listener, use safe calls (?.) to access views
            binding.ing.setBackgroundResource(R.drawable.btn1)
            binding.ing.setTextColor(getColor(R.color.white))
            binding.step.setTextColor(getColor(R.color.black))
            binding.step.background = null
            binding.stepScroll.visibility = View.GONE
            binding.ingScroll.visibility = View.VISIBLE
        }

        binding.fullScreen.setOnClickListener {

            if (imgCrop) {
                binding.itemImage.scaleType = ImageView.ScaleType.FIT_CENTER
                Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemImage)
                binding.fullScreen.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP)
                binding.shade.visibility = View.GONE
                imgCrop = !imgCrop
            } else {
                binding.itemImage.scaleType = ImageView.ScaleType.CENTER_CROP
                Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemImage)
                binding.fullScreen.setColorFilter(null)
                binding.shade.visibility = View.GONE
                imgCrop = !imgCrop
            }
        }
        binding.backBtn.setOnClickListener {
            finish()
        }

    }
}