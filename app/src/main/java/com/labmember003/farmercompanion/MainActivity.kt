package com.labmember003.farmercompanion

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.airbnb.lottie.LottieAnimationView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.json.JSONObject
import java.lang.ref.WeakReference
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    //val CITY: String = "dhaka,bd"
    var CITY: String = "mumbai"
    val API: String = "06c921750b9a82d8f5d1294e1586276f" // Use API key

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var tvLatitude: TextView
    private lateinit var tvLongitude: TextView
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        //tvLatitude = findViewById(R.id.)
        //tvLongitude = findViewById(R.id.)
        getCurrentLocation()
/*
        val geocoder = Geocoder(this, Locale.getDefault())
        val addresses: List<Address> = geocoder.getFromLocation(latitude, longitude, 1)
        val cityName: String = addresses[0].getAddressLine(0)
        val stateName: String = addresses[0].getAddressLine(1)
        val countryName: String = addresses[0].getAddressLine(2)

 */



        val editText = findViewById<EditText>(R.id.editTextTextCityName)
        findViewById<ImageView>(R.id.imageView).setOnClickListener {
            WeatherTask(this, (editText.text).toString()).execute()
        }

        //WeatherTask(edittext.text).execute()



/*
        findViewById<Button>(R.id.button).setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            // start your next activity
            startActivity(intent)
        }

 */
        findViewById<ImageView>(R.id.back_button).setOnClickListener {
            onBackPressed()
        }

        //Toast.makeText(this, city+"dog", Toast.LENGTH_LONG).show()

    }


    private fun getCurrentLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                 if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermission()
                    return
                }
                fusedLocationProviderClient.lastLocation.addOnCompleteListener(this) {
                    val location: Location? = it.result
                    if (location == null) {
                        Toast.makeText(this, "MEOW ?", Toast.LENGTH_SHORT).show()
                    } else {
                        //Toast.makeText(this, "SUCCESS", Toast.LENGTH_SHORT).show()
                        latitude = location.latitude
                        //Toast.makeText(this, "MEOW ?${location.latitude}", Toast.LENGTH_SHORT).show()
                        longitude = location.longitude
                        /*
                        val geocoder = Geocoder(this, Locale.getDefault())
                        val addresses: List<Address> = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                        val cityName: String = addresses[0].getAddressLine(0)
                        Toast.makeText(this, cityName, Toast.LENGTH_SHORT).show()
//                        val stateName: String = addresses[0].getAddressLine(1)
                         */
                        val city = getCity(location.latitude, location.longitude)
                        WeatherTask(this, city).execute()
                    }
                }
            } else {
                Toast.makeText(this, "Please Turn On Location", Toast.LENGTH_SHORT).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        }
        else {
            requestPermission()
        }
    }

    private fun getCity(latitude: Double, longitude: Double): String {
        var city = ""
        val gcd = Geocoder(this, Locale.getDefault())
        val addressess = gcd.getFromLocation(latitude, longitude, 1)
        if (addressess.size > 0) {
            println(addressess[0].locality)
            //Toast.makeText(this, addressess[0].locality+"nimbu", Toast.LENGTH_SHORT).show()
            city = addressess[0].locality
        }
        setEditTextDefaultValue(city)
        return city
    }

    private fun setEditTextDefaultValue(city: String) {
        val editTextTextCityName = findViewById<EditText>(R.id.editTextTextCityName)
        editTextTextCityName.setText(city)
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_REQUEST_ACCESS_LOCATION)
    }

    private fun checkPermissions(): Boolean {
        if ((ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
        == PackageManager.PERMISSION_GRANTED) && (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED))
        {
            return true
        }
        return false
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_ACCESS_LOCATION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
                getCurrentLocation()
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val PERMISSION_REQUEST_ACCESS_LOCATION = 100
    }



    inner class WeatherTask(
        act: Activity,
        private val city: String
    ) : AsyncTask<String, Void, String>() {

        private val activityRef = WeakReference(act)

        override fun onPreExecute() {
            super.onPreExecute()
            /* Showing the ProgressBar, Making the main design GONE */
            findViewById<LottieAnimationView>(R.id.loader).visibility = View.VISIBLE
            findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.GONE
            findViewById<TextView>(R.id.errorText).visibility = View.GONE
        }

        override fun doInBackground(vararg params: String?): String? {
            var response:String?
            try{
                //TODO(MAUSI)
                //Toast.makeText(this, )
                response = URL("https://api.openweathermap.org/data/2.5/weather?q=$city&units=metric&appid=$API").readText(
                    Charsets.UTF_8
                )
            } catch (e: Exception){
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                /* Extracting JSON returns from the API */
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")

                // val rain = jsonObj.getJSONObject("rain") ///////////wkwfwnw todo

                val sys = jsonObj.getJSONObject("sys")
                val wind = jsonObj.getJSONObject("wind")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)
                val crop = intent.getStringExtra("crop") // todo may crash
                findViewById<TextView>(R.id.crop_name).text = "$crop:"
                val id = weather.getInt("id") // mausi todo


                val updatedAt:Long = jsonObj.getLong("dt")
                val updatedAtText = "Updated at: "+ SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(
                    Date(updatedAt*1000)
                )
                val temp = main.getString("temp")+"°C"
                val tempMin = "Min Temp: " + main.getString("temp_min")+"°C"
                val tempMax = "Max Temp: " + main.getString("temp_max")+"°C"
                val tempMinInt = (main.getString("temp_min")).toFloat()
                //Toast.makeText(activityRef.get(), main.getString("temp_min"), Toast.LENGTH_SHORT).show()
                val tempMaxInt = (main.getString("temp_max")).toFloat()
                //Toast.makeText(activityRef.get(), tempMaxInt, Toast.LENGTH_SHORT).show()



                // WOOOOOOOOOFFFFFFF
                //250



                val pressure = main.getString("pressure")
                val humidity = main.getInt("humidity")
                val sunrise:Long = sys.getLong("sunrise")
                val sunset:Long = sys.getLong("sunset")
                val windSpeed = wind.getString("speed")
                val weatherDescription = weather.getString("description")

                val weatherShortDescp = weather.getString("main") //mausi todo

                val address = jsonObj.getString("name")+", "+sys.getString("country")

                val rainfallWork = findViewById<TextView>(R.id.rainfallWork)

                if (isRainfallOk(crop, id) == -1) {
                    rainfallWork.text = "• RainFall Low, Prepare Irrigation System"
                } else if (isRainfallOk(crop, id) == 0) {
                    rainfallWork.text = "• RainFall is Ok, Nothing to worry"
                } else {
                    rainfallWork.text = "• RainFall High, Prepare shelters, Open water absorbing System"
                }

                val temperatureWork = findViewById<TextView>(R.id.temperatureWork)
                if (isTempOk(crop, tempMinInt) == -1) {
                    temperatureWork.text = "• Temperature is Low, Prepare heating system"
                } else if (isTempOk(crop, tempMaxInt) == 0) {
                    temperatureWork.text = "• Temperature is OK, Nothing to worry"
                } else {
                    temperatureWork.text = "• Temperature is High, Prepare cooling system"
                }



                val humidityWork = findViewById<TextView>(R.id.humidityWork)
                if (isHumidityOk(crop, humidity) == -1) {
                    humidityWork.text = "• Humidity Low, Prepare Humidifier system"
                } else if (isHumidityOk(crop, humidity) == 0) {
                    humidityWork.text = "• Humidity is OK, Nothing to worry"
                } else {
                    humidityWork.text = "• Humidity High, Prepare Humidifier system"
                }







                findViewById<TextView>(R.id.address).text = address
                findViewById<TextView>(R.id.updated_at).text =  updatedAtText
                findViewById<TextView>(R.id.status).text = weatherDescription.capitalize()
                findViewById<TextView>(R.id.temp).text = temp
                findViewById<TextView>(R.id.temp_min).text = tempMin
                findViewById<TextView>(R.id.temp_max).text = tempMax
                findViewById<TextView>(R.id.sunrise).text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(
                    Date(sunrise*1000)
                )
                findViewById<TextView>(R.id.sunset).text = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(
                    Date(sunset*1000)
                )
                findViewById<TextView>(R.id.wind).text = windSpeed
                findViewById<TextView>(R.id.pressure).text = pressure
                //Toast.makeText(activityRef.get(), humidity.toString(), Toast.LENGTH_SHORT).show()
                findViewById<TextView>(R.id.humidity).text = humidity.toString()

                /* Views populated, Hiding the loader, Showing the main design */
                findViewById<LottieAnimationView>(R.id.loader).visibility = View.GONE
                findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.VISIBLE

            } catch (e: Exception) {
                findViewById<LottieAnimationView>(R.id.loader).visibility = View.GONE
                findViewById<TextView>(R.id.errorText).visibility = View.VISIBLE
            }

        }

        private fun isHumidityOk(crop: String?, humidity: Int): Int {
            if (crop == "Rice") {
                if (humidity < 60) {
                    return -1
                } else if (humidity > 80) {
                    return 1
                } else {
                    return 0
                }
            } else if (crop == "Wheat") {
                if (humidity < 50) {
                    return -1
                } else if (humidity > 60) {
                    return 1
                } else {
                    return 0
                }

            } else if (crop == "Millets") {
                if (humidity < 11) {
                    return -1
                } else if (humidity > 25) {
                    return 1
                } else {
                    return 0
                }
            } else {
                if (humidity < 13) {
                    return -1
                } else if (humidity > 15) {
                    return 1
                } else {
                    return 0
                }
            }
        }

        private fun isTempOk(crop: String?, temp: Float): Int {
            if (crop == "Rice") {
                if (temp < 25.0) {
                    return -1
                } else if (temp > 25.0) {
                    return 1
                } else {
                    return 0
                }
            } else if (crop == "Wheat") {
                if (temp < 14.0) {
                    return -1
                } else if (temp > 18.0) {
                    return 1
                } else {
                    return 0
                }

            } else if (crop == "Millets") {
                if (temp < 26.0) {
                    return -1
                } else if (temp > 29.0) {
                    return 1
                } else {
                    return 0
                }
            } else {
                if (temp < 21.0) {
                    return -1
                } else if (temp > 27.0) {
                    return 1
                } else {
                    return 0
                }
            }
        }

        private fun isRainfallOk(crop: String?, id: Int): Int {
            if (crop == "Rice") {
                if (id <= 300) {
                    return 0
                } else {
                    return -1
                }
            } else if (crop == "Wheat") {
                if (id <= 300) {
                    return 0
                } else {
                    return -1
                }

            } else if (crop == "Millets") {
                if (id <= 300) {
                    return 0
                } else {
                    return -1
                }

            } else {
                if (id <= 300) {
                    return 0
                } else {
                    return -1
                }
            }
        }


    }
}