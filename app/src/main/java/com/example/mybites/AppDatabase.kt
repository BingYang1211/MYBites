package com.example.mybites

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mybites.model.Grocery
import com.example.mybites.model.Recipe

@Database(entities = [Recipe::class, Grocery::class], version = 4, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): Dao

    companion object {
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Write your migration code here if needed
            }
        }
    }
}