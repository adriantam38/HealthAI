package com.example.comp3330_project

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Test_CaloriesTrackerView:AppCompatActivity() {
    private lateinit var caloriesSqLiteHelper: CaloriesSQLiteHelper
    private lateinit var id: TextView
    private lateinit var userID: TextView
    private lateinit var name: TextView
    private lateinit var value: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_activity_calories_record_veiwer)

        var viewButton: Button = findViewById(R.id.test_button)
        viewButton.setOnClickListener { getCalories() }
        caloriesSqLiteHelper = CaloriesSQLiteHelper(this)

        id = findViewById(R.id.RecordID)
        userID = findViewById(R.id.UserID)
        name = findViewById(R.id.Name)
        value = findViewById(R.id.Value)
    }
    private fun getCalories() {
        val stdList = caloriesSqLiteHelper.getAllRecords()
        val std = stdList[0]
        Log.e("pppp", "${stdList.size}")

        id.text = std.id.toString()
        userID.text = std.userID.toString()
        name.text = std.name
        value.text = std.calories.toString()
    }
}