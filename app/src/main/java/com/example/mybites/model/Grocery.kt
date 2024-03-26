package com.example.mybites.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "groceries")
class Grocery(
    @PrimaryKey(autoGenerate = true)
    var uid: Long = 0,

    @ColumnInfo(name = "prodImg")
    var prodImg: String,

    @ColumnInfo(name = "prodName")
    var prodName: String,

    @ColumnInfo(name = "dcrp")
    var dcrp: String,

    @ColumnInfo(name = "price") // Use REAL for price
    var price: String,

    @ColumnInfo(name = "category")
    var category: String
)