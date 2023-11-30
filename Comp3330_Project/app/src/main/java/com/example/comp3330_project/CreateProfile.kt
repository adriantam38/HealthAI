package com.example.comp3330_project

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


class CreateProfile : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var ageEditText: EditText

    private lateinit var gender: RadioGroup
    private var selectedGender: Int = 0

    private lateinit var heightEditText: EditText
    private lateinit var weightEditText: EditText

    private lateinit var activity: RadioGroup
    private var selectedActivity: Int = 0

    private lateinit var passwordEditText: EditText

    private lateinit var userSQListeHelper: UserSQLiteHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val toolbar = findViewById<Toolbar>(R.id.create_toolbar)
        setSupportActionBar(toolbar)

        val textView = toolbar.findViewById(R.id.toolbar_name) as TextView
        textView.setText("Create Profile")

        val returnButton = toolbar.findViewById(R.id.returnButton) as ImageButton
        returnButton.setOnClickListener {
            changeActivity("Activity")
        }

        nameEditText = findViewById(R.id.nameEditText)

        ageEditText = findViewById(R.id.ageEditText)
        weightEditText = findViewById(R.id.weightEditText)

        gender = findViewById<RadioGroup>(R.id.gender_radio_group)
        gender.setOnCheckedChangeListener { _, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            if (radio.text == "Male") {
                selectedGender = 0
            } else if (radio.text == "Female") {
                selectedGender = 1
            }
        }

        activity = findViewById<RadioGroup>(R.id.activity_radio_group)
        activity.setOnCheckedChangeListener { _, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            if (radio.text == "High") {
                selectedActivity = 0
            } else if (radio.text == "Low") {
                selectedActivity = 1
            }

        }

        userSQListeHelper = UserSQLiteHelper(this)

        passwordEditText = findViewById(R.id.PasswordEditText)

        val saveButton: Button = findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            addRecord()
            changeActivity("Menu")
        }
    }

    private fun addRecord() {

        if (nameEditText.text.isEmpty() || ageEditText.text.isEmpty() || weightEditText.text.isEmpty() ||
            heightEditText.text.isEmpty() || passwordEditText.text.isEmpty()
        ) {
            Toast.makeText(this, "Please enter the required field", Toast.LENGTH_LONG).show()
        } else {
            val name = nameEditText.text.toString()
            val age = ageEditText.text.toString().toInt()
            val weight = weightEditText.text.toString().toFloat()
            val height = heightEditText.text.toString().toFloat()
            val password = passwordEditText.text.toString()
            val std = UserModel(
                name = name, age = age, height = height, weight = weight,
                gender = selectedGender, activityLevel = selectedActivity, password = password
            )
            val status = userSQListeHelper.insertRecord(std)
            if (status > -1){
                Toast.makeText(this, "Record added", Toast.LENGTH_SHORT).show()
                clearEditText()
            } else{
                Toast.makeText(this, "Record not added", Toast.LENGTH_LONG).show()
            }

            Toast.makeText(this, password, Toast.LENGTH_LONG).show()
        }
    }

    private fun changeActivity(string: String) {
        if (string == "Activity") {
            val intent = Intent(this@CreateProfile, MainActivity::class.java)
            startActivity(intent)
        } else if (string == "Menu") {
            val intent = Intent(this@CreateProfile, MainMenu::class.java)
            startActivity(intent)
        }
    }

    private fun clearEditText(){
        nameEditText.setText("")
        ageEditText.setText("")
        heightEditText.setText("")
        weightEditText.setText("")
        val genderRadioGroup:RadioGroup = findViewById(R.id.gender_radio_group)
        genderRadioGroup.clearCheck()
        val activityRadioGroup:RadioGroup = findViewById(R.id.activity_radio_group)
        activityRadioGroup.clearCheck()
        passwordEditText.setText("")
        nameEditText.requestFocus()
    }
}