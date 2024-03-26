package com.example.mybites
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.mybites.adapter.CategoryAdapter
import com.example.mybites.databinding.ActivityCategoryBinding
import com.example.mybites.model.Recipe

class CategoryActivity : AppCompatActivity() {
    private lateinit var rvAdapter: CategoryAdapter
    private lateinit var dataList: ArrayList<Recipe>
    private val binding by lazy {
        ActivityCategoryBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = intent.getStringExtra("CATEGORY")
        // Set the CategoryTitle
        binding.CategoryTitle.text = title
        setUpRecyclerView()
        binding.goBackHome.setOnClickListener {
            finish()
        }
    }

    private fun setUpRecyclerView() {
        dataList = ArrayList()

        binding.rvCategory.layoutManager =
            LinearLayoutManager(this)
        val db = Room.databaseBuilder(this@CategoryActivity, AppDatabase::class.java, "db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()
        val daoObject = db.getDao()
        val recipes = daoObject.getAll()

        // Extract the category title from the intent
        val categoryTitle = intent.getStringExtra("CATEGORY") ?: ""

        for (i in recipes!!.indices) {
            if (recipes[i]!!.category.contains(categoryTitle)) {
                dataList.add(recipes[i]!!)
            }
        }

        // Pass the category title to the adapter
        rvAdapter = CategoryAdapter(dataList, this, categoryTitle)
        binding.rvCategory.adapter = rvAdapter
    }
}
