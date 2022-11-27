package com.labmember003.farmercompanion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import kotlin.system.exitProcess

class ToolsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tools)



        findViewById<TextView>(R.id.tv1).setOnClickListener {
            val intent = Intent(this, BuyActivity::class.java)
            //intent.putExtra("crop", "Millets")
            startActivity(intent)
        }
        findViewById<TextView>(R.id.tv2).setOnClickListener {
            val intent = Intent(this, LendActivity::class.java)
            //intent.putExtra("crop", "Millets")
            startActivity(intent)
        }



        // BOTTOM NAVIGATION
        findViewById<ImageView>(R.id.nv1).setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
//        findViewById<ImageView>(R.id.nv2).setOnClickListener {
//            val intent = Intent(this, ToolsActivity::class.java)
//            startActivity(intent)
//        }
        findViewById<ImageView>(R.id.nv3).setOnClickListener {
            val intent = Intent(this, LandActivity::class.java)
            startActivity(intent)
        }
        findViewById<ImageView>(R.id.nv4).setOnClickListener {
            val intent = Intent(this, CropHealthActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onBackPressed() {
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
    }

}