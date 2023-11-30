package com.example.comp3330_project

import android.content.Context
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


class CreateProfile: AppCompatActivity(){
    private lateinit var ageEditText: EditText

    private lateinit var gender: RadioGroup
    private var selectedGender: Int = 0

    private lateinit var heightEditText: EditText
    private lateinit var weightEditText: EditText

    private lateinit var activity: RadioGroup
    private var selectedActivity: Int = 0

    private lateinit var passwordEditText: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val toolbar = findViewById<Toolbar>(R.id.create_toolbar)
        setSupportActionBar(toolbar)

        val textView = toolbar.findViewById(R.id.toolbar_name) as TextView
        textView.text = "Create Profile"

        val returnButton = toolbar.findViewById(R.id.returnButton) as ImageButton
        returnButton.setOnClickListener {
            changeActivity("Activity")
        }


        ageEditText = findViewById(R.id.ageEditText)
        weightEditText = findViewById(R.id.weightEditText)

        gender = findViewById<RadioGroup>(R.id.gender_radio_group)
        gender.setOnCheckedChangeListener { _, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            if (radio.text == "Male"){
                selectedGender = 0
            }
            else if (radio.text == "Female"){
                selectedGender = 1
            }
        }

        activity = findViewById<RadioGroup>(R.id.activity_radio_group)
        activity.setOnCheckedChangeListener { _, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            if (radio.text == "High"){
                selectedActivity = 0
            }
            else if (radio.text == "Low"){
                selectedActivity = 1
            }

        }

        passwordEditText = findViewById(R.id.PasswordEditText)

        val saveButton: Button = findViewById(R.id.saveButton)
        saveButton.setOnClickListener{
            saveUserProfile()
        }
    }

    private fun saveUserProfile(){
        val age = ageEditText.text.toString().toIntOrNull() ?:0
        val weight = weightEditText.text.toString().toFloatOrNull() ?:0
        val height = heightEditText.text.toString().toFloatOrNull() ?:0
        val password = passwordEditText.text.toString()

        if (age == 0 || weight == 0.0F || height == 0.0F || password.isEmpty()){
            Toast.makeText(this, "Please enter the required field", Toast.LENGTH_LONG).show()
        }
        else {
            val sharedPref = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.apply {
                putInt("Age", age)
                putInt("Gender", selectedGender)
                putFloat("Weight", weight)
                putFloat("Height", height)
                putInt("ActivityLevel", selectedActivity)
                putString("password", password)
            }.apply()

            Toast.makeText(this, password, Toast.LENGTH_LONG).show()
            //changeActivity("Menu")
        }
    }

    private fun changeActivity(string: String){
        if (string == "Activity"){
            val intent = Intent(this@CreateProfile, MainActivity::class.java)
            startActivity(intent)
        }
        else if (string == "Menu"){
            val intent = Intent(this@CreateProfile, MainMenu::class.java)
            startActivity(intent)
        }
    }
}
