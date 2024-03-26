package com.example.mybites

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.mybites.adapter.SearchAdapter
import com.example.mybites.databinding.ActivitySearchBinding
import com.example.mybites.model.Recipe


class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var rvAdapter: SearchAdapter
    private lateinit var dataList: ArrayList<Recipe>
    private lateinit var recipes: List<Recipe?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchRecipe.requestFocus()
        setUpRecyclerView()

        binding.goBackHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.groceryTab.setOnClickListener{
            val intent = Intent(this, GroceriesSearchActivity::class.java)
            startActivity(intent)

        }


        binding.searchRecipe.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isNotEmpty()) {
                    filterData(s.toString())
                } else {
                    // If the search bar is empty, show all recipes based on the selected tab
                    setUpRecyclerView()
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filterData(filterText: String) {
        var filterData = ArrayList<Recipe>()
        for (i in recipes.indices) {
            if (recipes[i]?.tittle?.lowercase()?.contains(filterText.lowercase()) == true) {
                filterData.add(recipes[i]!!)
            }
        }
        rvAdapter.filterList(filterList = filterData)
    }

    private fun setUpRecyclerView() {
        dataList= ArrayList()

        binding.rvRecipes.layoutManager = LinearLayoutManager(this)

        // Database initialization and data retrieval for recipes...
        var db = Room.databaseBuilder(this@SearchActivity, AppDatabase::class.java, "db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()
        var daoObject = db.getDao()
        recipes = daoObject.getAll()
        for (i in recipes !!.indices){
            if(recipes[i]!!.category.contains("Popular")){
                dataList.add(recipes[i]!!)
            }
        }
        rvAdapter = SearchAdapter(dataList, this)
        binding.rvRecipes.adapter = rvAdapter
    }

}

