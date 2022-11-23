package com.labmember003.farmercompanion

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

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
    }

}