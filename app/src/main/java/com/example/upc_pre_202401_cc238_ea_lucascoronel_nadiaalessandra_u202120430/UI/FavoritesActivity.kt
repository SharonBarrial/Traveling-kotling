package com.example.upc_pre_202401_cc238_ea_lucascoronel_nadiaalessandra_u202120430.UI

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.upc_pre_202401_cc238_ea_lucascoronel_nadiaalessandra_u202120430.R
import com.example.upc_pre_202401_cc238_ea_lucascoronel_nadiaalessandra_u202120430.adapter.FavoriteAdapter
import com.example.upc_pre_202401_cc238_ea_lucascoronel_nadiaalessandra_u202120430.database.AppDatabase
import com.example.upc_pre_202401_cc238_ea_lucascoronel_nadiaalessandra_u202120430.model.FavoritePackage

class FavoritesActivity : AppCompatActivity(), FavoriteAdapter.OnItemClickListener {
    private lateinit var favoritePackageAdapter: FavoriteAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_favorites)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get the return button
        val ibReturnToMain = findViewById<ImageButton>(R.id.ibReturnToHome)

        // Initialize the adapter only once here
        favoritePackageAdapter = FavoriteAdapter(emptyList(), this)

        // Return to the main activity
        ibReturnToMain.setOnClickListener{
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Load data from the database and update the RecyclerView
        loadPackages()
    }

    private fun loadPackages() {
        val dao = AppDatabase.getInstance(this).getDao()
        val favoritePackage = dao.getAll()

        // Update the adapter with the data obtained from the database
        favoritePackageAdapter.updatePackage(favoritePackage)

        // Get the RecyclerView
        val rvFavorites = findViewById<RecyclerView>(R.id.rvFavorites)

        // Set the adapter and layout manager for the RecyclerView
        rvFavorites.adapter = favoritePackageAdapter
        rvFavorites.layoutManager = LinearLayoutManager(this)
    }

    override fun onItemClick(favoritePackage: FavoritePackage) {
        val dao = AppDatabase.getInstance(this).getDao()
        dao.deleteItem(favoritePackage)

        Toast.makeText(this, "Package  "+favoritePackage.nombre +" deleted from favorites", Toast.LENGTH_SHORT).show()

        loadPackages()
    }
}