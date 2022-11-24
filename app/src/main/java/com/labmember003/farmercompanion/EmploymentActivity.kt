package com.labmember003.farmercompanion

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class EmploymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employment)
        findViewById<ImageView>(R.id.farmDirection).setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?34.34&daddr=28.66488568388205,77.30043327394083")
            )
            startActivity(intent)
        }
        findViewById<ImageView>(R.id.farmDirection2).setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?34.34&daddr=28.66488568388205,77.30043327394083")
            )
            startActivity(intent)
        }
        findViewById<ImageView>(R.id.farmDirection3).setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?34.34&daddr=28.66488568388205,77.30043327394083")
            )
            startActivity(intent)
        }
    }
}