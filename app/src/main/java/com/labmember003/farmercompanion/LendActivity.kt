package com.labmember003.farmercompanion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlin.system.exitProcess

class LendActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.simple_android_contact_form_ui_design)
        findViewById<Button>(R.id.submit).setOnClickListener {
            Toast.makeText(this, "Successfully Submited", Toast.LENGTH_SHORT).show()
        }
    }

}