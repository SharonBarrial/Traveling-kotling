package com.example.upc_pre_202401_cc238_ea_lucascoronel_nadiaalessandra_u202120430.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.upc_pre_202401_cc238_ea_lucascoronel_nadiaalessandra_u202120430.model.FavoritePackage

@Dao
interface FavoritePackageDao {
    //crud
    //Room just works with List
    @Query("SELECT * FROM FavoritePackage")
    fun getAll(): List<FavoritePackage>

    @Insert
    fun insertItem(vararg favoritePackage: FavoritePackage)

    @Delete
    fun deleteItem(vararg favoritePackage: FavoritePackage)

    @Update
    fun updateItem(vararg favoritePackage: FavoritePackage)
}