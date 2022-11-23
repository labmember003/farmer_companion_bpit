package com.labmember003.farmercompanion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class LendFarmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lend_farm)
        findViewById<Button>(R.id.submit).setOnClickListener {
            Toast.makeText(this, "Successfully Submited", Toast.LENGTH_SHORT).show()
        }
    }

}