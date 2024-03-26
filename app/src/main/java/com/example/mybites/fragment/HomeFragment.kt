package com.example.mybites.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.mybites.AppDatabase
import com.example.mybites.CategoryActivity
import com.example.mybites.adapter.PopularAdapter
import com.example.mybites.ProdCategoryActivity
import com.example.mybites.model.Recipe
import com.example.mybites.SearchActivity
import com.example.mybites.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var rvAdapter: PopularAdapter
    private lateinit var dataList: ArrayList<Recipe>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()

        binding.search.setOnClickListener {
            Log.d("SearchActivity", "Search button clicked")
            val intent = Intent(requireContext(), SearchActivity::class.java)
            startActivity(intent)
        }

        binding.malaycuisines.setOnClickListener {
            val myIntent = Intent(requireContext(), CategoryActivity::class.java)
            myIntent.putExtra("TITTLE", "Malay")
            myIntent.putExtra("CATEGORY", "Malay")
            startActivity(myIntent)
        }

        binding.indiancuisines.setOnClickListener {
            val myIntent = Intent(requireContext(), CategoryActivity::class.java)
            myIntent.putExtra("TITTLE", "Indian")
            myIntent.putExtra("CATEGORY", "Indian")
            startActivity(myIntent)
        }

        binding.chinesecuisines.setOnClickListener {
            val myIntent = Intent(requireContext(), CategoryActivity::class.java)
            myIntent.putExtra("TITTLE", "Chinese")
            myIntent.putExtra("CATEGORY", "Chinese")
            startActivity(myIntent)
        }

        binding.dessertscuisines.setOnClickListener {
            val myIntent = Intent(requireContext(), CategoryActivity::class.java)
            myIntent.putExtra("TITTLE", "Dessert")
            myIntent.putExtra("CATEGORY", "Dessert")
            startActivity(myIntent)

        }

        binding.ricegroceries.setOnClickListener {
            val myIntent = Intent(requireContext(), ProdCategoryActivity::class.java)
            myIntent.putExtra("TITLE", "Rice")
            myIntent.putExtra("CATEGORY", "Rice")
            startActivity(myIntent)
        }

        binding.cendimentsgroceries.setOnClickListener {
            val myIntent = Intent(requireContext(), ProdCategoryActivity::class.java)
            myIntent.putExtra("TITLE", "Cendiments")
            myIntent.putExtra("CATEGORY", "Cendiments")
            startActivity(myIntent)
        }

        binding.vegegroceries.setOnClickListener {
            val myIntent = Intent(requireContext(), ProdCategoryActivity::class.java)
            myIntent.putExtra("TITLE", "Vegetables")
            myIntent.putExtra("CATEGORY", "Vegetables")
            startActivity(myIntent)
        }

        binding.fruitsgroceries.setOnClickListener {
            val myIntent = Intent(requireContext(), ProdCategoryActivity::class.java)
            myIntent.putExtra("TITLE", "Fruits")
            myIntent.putExtra("CATEGORY", "Fruits")
            startActivity(myIntent)
        }

        binding.meatsgroceries.setOnClickListener {
            val myIntent = Intent(requireContext(), ProdCategoryActivity::class.java)
            myIntent.putExtra("TITLE", "Meats")
            myIntent.putExtra("CATEGORY", "Meats")
            startActivity(myIntent)
        }

        binding.milkgroceries.setOnClickListener {
            val myIntent = Intent(requireContext(), ProdCategoryActivity::class.java)
            myIntent.putExtra("TITLE", "Milk & Eggs")
            myIntent.putExtra("CATEGORY", "Milk & Eggs")
            startActivity(myIntent)
        }
    }

    private fun setUpRecyclerView() {
        dataList = ArrayList()

        binding.rvPopular.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val db = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java,
            "db_name"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()

        val daoObject = db.getDao()
        val recipes = daoObject.getAll()

        for (i in recipes!!.indices) {
            if (recipes[i]!!.category.contains("Popular")) {
                dataList.add(recipes[i]!!)
            }
            rvAdapter = PopularAdapter(dataList, requireContext())
            binding.rvPopular.adapter = rvAdapter

        }
    }
}
