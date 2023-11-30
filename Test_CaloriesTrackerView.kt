package com.example.comp3330_project

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Test_CaloriesTrackerView:AppCompatActivity() {
    private lateinit var sqLiteHelper: SQLiteHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_activity_calories_record_veiwer)

        var viewButton: Button = findViewById(R.id.test_button)
        viewButton.setOnClickListener { getCalories() }
        sqLiteHelper = SQLiteHelper(this)
    }
    private fun getCalories() {
        val stdList = sqLiteHelper.getAllRecords()
        Log.e("pppp", "${stdList.size}")
    }
}