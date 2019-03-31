package com.example.basicweatherapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val API = "1132b92a0baff313f247f99f41f39903"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val queue = Volley.newRequestQueue(this)
        val url = "http://api.openweathermap.org/data/2.5/weather?zip=70810,us&units=imperial&APPID=$API"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                val mainWeather = response.getJSONObject("main").getString("temp")
                val minWeather = response.getJSONObject("main").getString("temp_min")
                val maxWeather = response.getJSONObject("main").getString("temp_max")
                val cityName = response.getString("name")
                val weatherInfo = response.getJSONArray("weather").getJSONObject(0).getString("main")
                val weatherIcon = response.getJSONArray("weather").getJSONObject(0).getString("icon")
                weatherInfo_text.text = weatherInfo
                weather_Text.text = mainWeather
                cityName_text.text = cityName
                minWeather_text.text = minWeather
                maxWeather_text.text = maxWeather

            },
            Response.ErrorListener { error ->
                Toast.makeText(this, "Connection Error", Toast.LENGTH_SHORT).show()
            }
        )


        queue.add(jsonObjectRequest )
    }
}
