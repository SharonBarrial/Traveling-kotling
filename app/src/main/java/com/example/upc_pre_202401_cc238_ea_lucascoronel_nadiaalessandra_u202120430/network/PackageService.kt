package com.example.upc_pre_202401_cc238_ea_lucascoronel_nadiaalessandra_u202120430.network

import com.example.upc_pre_202401_cc238_ea_lucascoronel_nadiaalessandra_u202120430.model.Package
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PackageService {

    @GET("productossitiotipo.php")
    fun getPackages(
        @Query("sitio") sitio: String,
        @Query("tipo") tipo: String
    ): Call<List<Package>>
}