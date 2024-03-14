package com.example.elec4848_sdp.views.function_views

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.elec4848_sdp.functions.trackers.exercise.ExerciseModel
import com.example.elec4848_sdp.functions.trackers.exercise.ExerciseSQLiteHelper
import com.example.elec4848_sdp.R
import com.example.elec4848_sdp.functions.profile.UserSQLiteHelper
import com.example.elec4848_sdp.views.main_views.MainMenu

class ExerciseTracker : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var durationEditText: EditText
    private lateinit var intensityRadioGroup: RadioGroup
    private lateinit var intensityRadioButton: RadioButton
    private lateinit var caloriesEditText: EditText
    private lateinit var exerciseAddButton: Button

    private lateinit var exerciseSqLiteHelper: ExerciseSQLiteHelper
    private lateinit var userSqLiteHelper: UserSQLiteHelper

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercisetracker)

        val toolbar = findViewById<Toolbar>(R.id.create_toolbar)
        setSupportActionBar(toolbar)

        val textView = toolbar.findViewById(R.id.toolbar_name) as TextView
        textView.text = "Exercise Tracker"

        val returnButton = toolbar.findViewById(R.id.returnButton) as ImageButton
        returnButton.setOnClickListener {
            changeActivity()
        }

        nameEditText = findViewById(R.id.nameEditText)
        durationEditText = findViewById(R.id.DurationEditText)
        intensityRadioGroup = findViewById(R.id.intensityRadioGroup)
        caloriesEditText = findViewById(R.id.CaloriesEditText)
        exerciseAddButton = findViewById(R.id.exerciseAddButton)
        exerciseSqLiteHelper = ExerciseSQLiteHelper(this)
        userSqLiteHelper = UserSQLiteHelper(this)

        exerciseAddButton.setOnClickListener { addRecord() }
    }
    private fun addRecord() {
        val name = nameEditText.text.toString()
        val calories = caloriesEditText.text.toString().toIntOrNull()
        val duration = durationEditText.text.toString().toInt()

        val selectedOption: Int = intensityRadioGroup!!.checkedRadioButtonId
        intensityRadioButton = findViewById(selectedOption)
        val intensity = intensityRadioButton.text.toString()

        if (name.isEmpty() || calories == null) {
            Toast.makeText(this, "Please enter the required field", Toast.LENGTH_LONG).show()
        } else {
            val userID = userSqLiteHelper.getUserID()
            if (userID != 0) {
                val std = ExerciseModel(userID = userID, name = name, intensity = intensity, duration = duration, calories = calories)
                val status = exerciseSqLiteHelper.insertRecord(std)
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
        durationEditText.setText("")
        val selectedOption: RadioGroup = findViewById(R.id.intensityRadioGroup)
        selectedOption.clearCheck()
        nameEditText.requestFocus()
    }

    private fun changeActivity() {
        val intent = Intent(this, MainMenu::class.java)
        startActivity(intent)
    }

}