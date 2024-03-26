package com.example.mybites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.mybites.adapter.ProdCategoryAdapter
import com.example.mybites.databinding.ActivityProdCategoryBinding
import com.example.mybites.model.Grocery

class ProdCategoryActivity : AppCompatActivity() {

    private lateinit var rvAdapter: ProdCategoryAdapter
    private lateinit var dataList: ArrayList<Grocery>  // Declare dataList here
    private val binding by lazy {
        ActivityProdCategoryBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = intent.getStringExtra("CATEGORY")
        setUpRecyclerView()


        // Set the CategoryTitle
        binding.CategoryTitle.text = title

        binding.goBackHome.setOnClickListener {
            finish()
        }

    }

    private fun setUpRecyclerView() {
        dataList = ArrayList()

        binding.rvCategory.layoutManager =
            LinearLayoutManager(this)
        val db = Room.databaseBuilder(this@ProdCategoryActivity, AppDatabase::class.java, "db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()
        val daoObject = db.getDao()
        val groceries = daoObject.getAllGroceries()

        // Extract the category title from the intent
        val categoryTitle = intent.getStringExtra("CATEGORY") ?: ""

        for (i in groceries!!.indices) {
            if (groceries[i]!!.category.contains(categoryTitle)) {
                dataList.add(groceries[i]!!)
            }
        }

        // Pass the category title to the adapter
        rvAdapter = ProdCategoryAdapter(dataList, this, categoryTitle)
        binding.rvCategory.adapter = rvAdapter
    }
}

