package com.example.elec4848_sdp.views.function_views

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.elec4848_sdp.functions.trackers.calories.CaloriesModel
import com.example.elec4848_sdp.functions.trackers.calories.CaloriesSQLiteHelper
import com.example.elec4848_sdp.R
import com.example.elec4848_sdp.functions.profile.UserSQLiteHelper
import com.example.elec4848_sdp.views.main_views.MainMenu

class CaloriesTracker : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var caloriesEditText: EditText
    private lateinit var caloriesAddButton: Button

    private lateinit var caloriesSqLiteHelper: CaloriesSQLiteHelper
    private lateinit var userSqLiteHelper: UserSQLiteHelper

    private lateinit var recyclerView: RecyclerView
    private var adapter: CaloriesRecord? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caloriestracker)

        val toolbar = findViewById<Toolbar>(R.id.create_toolbar)
        setSupportActionBar(toolbar)

        val returnButton = toolbar.findViewById(R.id.returnButton) as ImageButton
        returnButton.setOnClickListener {
            changeActivity()
        }

        val textView = toolbar.findViewById(R.id.toolbar_name) as TextView
        textView.text = "Calories Tracker"

        nameEditText = findViewById(R.id.nameEditText)
        caloriesEditText = findViewById(R.id.DurationEditText)
        caloriesAddButton = findViewById(R.id.caloriesAddButton)
        caloriesSqLiteHelper = CaloriesSQLiteHelper(this)
        userSqLiteHelper = UserSQLiteHelper(this)

        caloriesAddButton.setOnClickListener { addRecord() }
    }

    private fun addRecord() {
        val name = nameEditText.text.toString()
        val calories = caloriesEditText.text.toString().toIntOrNull()

        if (name.isEmpty() || calories == null) {
            Toast.makeText(this, "Please enter the required field", Toast.LENGTH_LONG).show()
        } else {
            val userID = userSqLiteHelper.getUserID()
            if (userID != 0) {
                val std = CaloriesModel(name = name, userID = userID, calories = calories)
                val status = caloriesSqLiteHelper.insertRecord(std)
                if (status > -1) {
                    Toast.makeText(this, "Record added", Toast.LENGTH_LONG).show()
                    clearEditText()
                } else {
                    Toast.makeText(this, "Record not added", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "userID == 0", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun clearEditText() {
        nameEditText.setText("")
        caloriesEditText.setText("")
        nameEditText.requestFocus()
    }

    private fun changeActivity() {
        val intent = Intent(this, MainMenu::class.java)
        startActivity(intent)
    }

}