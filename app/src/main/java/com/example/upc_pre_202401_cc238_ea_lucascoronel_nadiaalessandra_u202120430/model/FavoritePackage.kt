package com.example.upc_pre_202401_cc238_ea_lucascoronel_nadiaalessandra_u202120430.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class FavoritePackage (
    @PrimaryKey(autoGenerate = true)
    val id: Int?,

    @ColumnInfo(name = "name")
    val nombre: String,

    @ColumnInfo(name = "description")
    val descripcion: String,

    @ColumnInfo(name = "picture")
    val imagen: String
)