package com.labmember003.farmercompanion

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.labmember003.farmercompanion.databinding.ActivityMain2Binding
import kotlin.system.exitProcess

class MainActivity2 : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        //setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //val navController = findNavController(R.id.nav_host_fragment_content_main)
        //appBarConfiguration = AppBarConfiguration(navController.graph)
        //NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)
        //NavigationUI.setupWithNavController(binding.navView, navController)





        setContentView(R.layout.activity_main2)
/*
        val a = listOf(R.id.year1, R.id.year2, R.id.year3, R.id.year4)
        a.forEach{
            findViewById<ImageView>(it).setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
                when (it) {
                    R.id.year1 ->  intent.putExtra("crop", "rice")
                }
                //intent.putExtra("crop", "rice")
                startActivity(intent)
            }
        }

 */
        findViewById<ImageView>(R.id.settingsImage).setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        findViewById<ImageView>(R.id.year1).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("crop", "Rice")
            startActivity(intent)
        }
        findViewById<ImageView>(R.id.year2).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("crop", "Wheat")
            startActivity(intent)
        }
        findViewById<ImageView>(R.id.year3).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("crop", "Millets")
            startActivity(intent)
        }
        findViewById<ImageView>(R.id.year4).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("crop", "Maize")
            startActivity(intent)
        }






        // BOTTOM NAVIGATION
        findViewById<ImageView>(R.id.nv1).setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
        findViewById<ImageView>(R.id.nv2).setOnClickListener {
            val intent = Intent(this, ToolsActivity::class.java)
            startActivity(intent)
        }
        findViewById<ImageView>(R.id.nv3).setOnClickListener {
            val intent = Intent(this, LandActivity::class.java)
            startActivity(intent)
        }
        findViewById<ImageView>(R.id.nv4).setOnClickListener {
            val intent = Intent(this, CropHealthActivity::class.java)
            startActivity(intent)
        }
        findViewById<FloatingActionButton>(R.id.fab).visibility = View.INVISIBLE
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)
            startActivity(intent)
        }


    }



}