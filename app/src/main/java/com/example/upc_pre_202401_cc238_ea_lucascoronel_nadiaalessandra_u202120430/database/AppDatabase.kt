package com.example.upc_pre_202401_cc238_ea_lucascoronel_nadiaalessandra_u202120430.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.upc_pre_202401_cc238_ea_lucascoronel_nadiaalessandra_u202120430.model.FavoritePackage

@Database(entities = [FavoritePackage::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getDao(): FavoritePackageDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room
                    .databaseBuilder(context, AppDatabase::class.java, "FavoritesPackages.db")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE as AppDatabase
        }
    }
}