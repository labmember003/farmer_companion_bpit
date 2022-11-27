package com.labmember003.farmercompanion

import android.R.attr
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import kotlin.system.exitProcess


class CropHealthActivity : AppCompatActivity() {
    lateinit var camera_open_id: Button
    lateinit var click_image_id: ImageView
    lateinit var text: TextView

    companion object {
        // Define the pic id
        private const val pic_id = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crop_health)

        // By ID we can get each component which id is assigned in XML file get Buttons and imageview.
        //val a = {"Rice", "Wheat"}
        val name = arrayOf("Rice", "Wheat", "Milets", "Maize").random()
        val Diaganosis = arrayOf("Risk for infection related to compromise immune system" ,"Risk for infection related to viruses", "Risk for infection related to fungi", "Risk for infection related to nematodes", "Risk for infection related to parasitic plants", "normal", "normal", "normal").random()
        //val c = "Risk for infection related to compromise immune system"
        val healthPercent = (50..100).random()
        val care = arrayOf("Limit use of pesticide", "Avoid use of artificial pesticide", "Increase Water Supply", "Deploy Drainage System").random()
        val plantName: String = name

        camera_open_id = findViewById(R.id.camera_button)
        click_image_id = findViewById(R.id.click_image)
        text = findViewById(R.id.plantAbout)
        text.text = "Name: ${name} \nDiagnosis: ${Diaganosis} \nPlant Health: ${healthPercent} % \nRecomended Care: ${care}"
        findViewById<TextView>(R.id.plantAbout).visibility = View.INVISIBLE



        // Camera_open button is for open the camera and add the setOnClickListener in this button
        camera_open_id.setOnClickListener {
            // Create the camera_intent ACTION_IMAGE_CAPTURE it will open the camera for capture the image
            val camera_intent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            } else {
                TODO("VERSION.SDK_INT < CUPCAKE")
            }
            // Start the activity with camera_intent, and request pic id
            startActivityForResult(camera_intent, pic_id)
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
//        findViewById<ImageView>(R.id.nv4).setOnClickListener {
//            val intent = Intent(this, CropHealthActivity::class.java)
//            startActivity(intent)
//        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Match the request 'pic id with requestCode
        if (requestCode == pic_id) {
            // BitMap is data structure of image file which store the image in memory
            val photo = data!!.extras!!["data"] as Bitmap?
            // Set the image in imageview for display
            click_image_id.setImageBitmap(photo)
        }
        camera_open_id.visibility = View.INVISIBLE
        findViewById<TextView>(R.id.plantAbout).visibility = View.VISIBLE
        findViewById<CardView>(R.id.cardView1).visibility = View.INVISIBLE

    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
    }





}