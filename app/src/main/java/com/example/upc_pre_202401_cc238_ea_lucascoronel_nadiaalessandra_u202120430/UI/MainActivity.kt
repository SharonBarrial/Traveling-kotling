package com.example.upc_pre_202401_cc238_ea_lucascoronel_nadiaalessandra_u202120430.UI

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.upc_pre_202401_cc238_ea_lucascoronel_nadiaalessandra_u202120430.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get the buttons
        val btFind = findViewById<Button>(R.id.btFind)
        val btFavorites = findViewById<Button>(R.id.btFavorites)


        // Set the click listeners redirecting to the corresponding activities
        btFind.setOnClickListener {
            val intent = Intent(this, PackagesActivity::class.java)
            startActivity(intent)
        }

        btFavorites.setOnClickListener {
            val intent = Intent(this, FavoritesActivity::class.java)
            startActivity(intent)

        }
    }
}