package com.example.mybites

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.mybites.adapter.GroceriesSearchAdapter
import com.example.mybites.databinding.ActivityGroceriesSearchBinding
import com.example.mybites.model.Grocery


class GroceriesSearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGroceriesSearchBinding
    private lateinit var rvAdapter: GroceriesSearchAdapter
    private lateinit var dataList: ArrayList<Grocery>
    private lateinit var groceries: List<Grocery?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGroceriesSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchGrocery.requestFocus()
        setUpRecyclerView()

        binding.goBackHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.recipeTab.setOnClickListener{
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)

        }


        binding.searchGrocery.addTextChangedListener(object : TextWatcher {
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
        var filterData = ArrayList<Grocery>()
        for (i in groceries.indices) {
            if (groceries[i]?.prodName?.lowercase()?.contains(filterText.lowercase()) == true) {
                filterData.add(groceries[i]!!)
            }
        }
        rvAdapter.filterList(filterList = filterData)
    }

    private fun setUpRecyclerView() {
        dataList= ArrayList()

        binding.rvGroceries.layoutManager = LinearLayoutManager(this)

        // Database initialization and data retrieval for recipes...
        var db = Room.databaseBuilder(this@GroceriesSearchActivity, AppDatabase::class.java, "db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()
        var daoObject = db.getDao()
        groceries = daoObject.getAllGroceries()
        for (i in groceries !!.indices){
            if(groceries[i]!!.category.contains("Popular")){
                dataList.add(groceries[i]!!)
            }
        }
        rvAdapter = GroceriesSearchAdapter(dataList, this)
        binding.rvGroceries.adapter = rvAdapter
    }

}

