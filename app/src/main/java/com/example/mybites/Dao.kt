package com.example.mybites

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mybites.model.Grocery
import com.example.mybites.model.Recipe

@Dao
interface Dao {

    @Query("SELECT * FROM recipe")

    fun getAll(): List<Recipe>

    @Insert
    fun setAll(recipes: List<Recipe>)

    @Query("SELECT * FROM groceries")
    fun getAllGroceries(): List<Grocery>

    @Insert
    fun setAllGroceries(groceries: List<Grocery>)

}