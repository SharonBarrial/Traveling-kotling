package com.example.upc_pre_202401_cc238_ea_lucascoronel_nadiaalessandra_u202120430.UI

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.upc_pre_202401_cc238_ea_lucascoronel_nadiaalessandra_u202120430.R
import com.example.upc_pre_202401_cc238_ea_lucascoronel_nadiaalessandra_u202120430.adapter.PackageAdapter
import com.example.upc_pre_202401_cc238_ea_lucascoronel_nadiaalessandra_u202120430.database.AppDatabase
import com.example.upc_pre_202401_cc238_ea_lucascoronel_nadiaalessandra_u202120430.model.FavoritePackage
import com.example.upc_pre_202401_cc238_ea_lucascoronel_nadiaalessandra_u202120430.model.Package
import com.example.upc_pre_202401_cc238_ea_lucascoronel_nadiaalessandra_u202120430.network.PackageService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PackagesActivity : AppCompatActivity(), PackageAdapter.OnItemClickListener {

    //This is the adapter for the RecyclerView
    private lateinit var packageAdapter: PackageAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_packages)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Here we initialize the adapter
        packageAdapter = PackageAdapter(emptyList(), this)

        val btSearch = findViewById<Button>(R.id.btSearch)
        val ibReturnToHome = findViewById<ImageButton>(R.id.ibReturnToHome)

        val rvPackages = findViewById<RecyclerView>(R.id.rvPackages)
        rvPackages.adapter = packageAdapter
        rvPackages.layoutManager = LinearLayoutManager(this)

        btSearch.setOnClickListener {
            searchPackages()
        }

        //return to the main activity
        ibReturnToHome.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onItemClick(pack: Package) {

        //Create a FavoritePackage object from the Package object
        val favoritePackage = FavoritePackage(
            id = null,
            nombre = pack.nombre,
            descripcion = pack.descripcion,
            imagen = pack.imagen
        )

        // Get the DAO from the database
        val dao = AppDatabase.getInstance(this).getDao()

        //Insert the FavoritePackage object into the database
        dao.insertItem(favoritePackage)

        //Show a toast indicating that the package was added to favorites
        Toast.makeText(this, "Package ${favoritePackage.nombre} added to favorites", Toast.LENGTH_SHORT).show()
    }


    private fun searchPackages() {
        //Create the Retrofit object
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dev.formandocodigo.com/ServicioTour/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val packageService = retrofit.create(PackageService::class.java)

        val etSideId = findViewById<EditText>(R.id.etSideId).text.toString()
        val etTypeId = findViewById<EditText>(R.id.etTypeId).text.toString()

        val request = packageService.getPackages(etSideId, etTypeId)

        //Make the request to the server
        request.enqueue(object : Callback<List<Package>> {
            override fun onResponse(call: Call<List<Package>>, response: Response<List<Package>>) {
                if (response.isSuccessful) {
                    val packages = response.body() ?: emptyList()
                    packageAdapter.updatePackage(packages)
                } else {
                    // Manage unsuccessful response
                    Log.e("PackagesActivity", "Unsuccessful response: ${response.message()}")
                    Toast.makeText(this@PackagesActivity, "Failed to load packages: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Package>>, t: Throwable) {
                //Manage failure
                Log.e("PackagesActivity", "onFailure: ${t.message}")
                Toast.makeText(this@PackagesActivity, "Failed to load packages: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}