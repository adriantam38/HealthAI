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
    private lateinit var heightEditText: EditText
    private lateinit var weightEditText: EditText

    private lateinit var male: RadioButton
    private lateinit var female: RadioButton
    private var selectedGender: Int = 0

    private lateinit var highLevel: RadioButton
    private lateinit var lowLevel: RadioButton
    private var selectedActivity: Int = 0

    private lateinit var passwordEditText: EditText

    private lateinit var userSQLiteHelper: UserSQLiteHelper

    private var std: UserModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        val mode = intent.getStringExtra("mode")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val toolbar = findViewById<Toolbar>(R.id.create_toolbar)
        setSupportActionBar(toolbar)

        val titleView = toolbar.findViewById(R.id.toolbar_name) as TextView
        if (mode == "Create") {
            titleView.text = "Create Profile"
        } else if (mode == "Modify") {
            titleView.text = "Modify Profile"
        }


        val returnButton = toolbar.findViewById(R.id.returnButton) as ImageButton
        returnButton.setOnClickListener {
            if (mode == "Create") {
                changeActivity("Activity")
            } else if (mode == "Modify") {
                changeActivity("Menu")
            }

        }

        nameEditText = findViewById(R.id.nameEditText)
        ageEditText = findViewById(R.id.ageEditText)
        heightEditText = findViewById(R.id.heightEditText)
        weightEditText = findViewById(R.id.weightEditText)
        male = findViewById(R.id.Male)
        female = findViewById(R.id.Female)
        highLevel = findViewById(R.id.High)
        lowLevel = findViewById(R.id.Low)

        userSQLiteHelper = UserSQLiteHelper(this)

        passwordEditText = findViewById(R.id.ProfileNameEditText)

        if (male.isChecked) {
            selectedGender = 0
        } else if (female.isChecked) {
            selectedGender = 1
        }

        if (highLevel.isChecked) {
            selectedActivity = 0
        } else if (lowLevel.isChecked) {
            selectedActivity = 1
        }

        if (mode == "Modify") {
            val stdList = userSQLiteHelper.getAllRecords()
            val std = stdList[0]
            nameEditText.setText(std?.name)
            ageEditText.setText(std?.age.toString())
            heightEditText.setText(std?.height.toString())
            weightEditText.setText(std?.weight.toString())
            passwordEditText.setText((std?.password))
            if (std?.gender == 0) {
                val gender: RadioButton = findViewById(R.id.Male)
                gender.isChecked = true
            } else if (std?.gender == 1) {
                val gender: RadioButton = findViewById(R.id.Female)
                gender.isChecked = true
            }
            if (std?.activityLevel == 0) {
                val level: RadioButton = findViewById(R.id.High)
                level.isChecked = true
            } else if (std?.activityLevel == 1) {
                val level: RadioButton = findViewById(R.id.Low)
                level.isChecked = true
            }
        }

        val saveButton: Button = findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            if (mode == "Create") {
                addRecord()
            } else if (mode == "Modify") {
                updateRecord()
            }
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
            val status = userSQLiteHelper.insertRecord(std)
            if (status > -1) {
                Toast.makeText(this, "Record added", Toast.LENGTH_SHORT).show()
                clearEditText()
            } else {
                Toast.makeText(this, "Record not added", Toast.LENGTH_LONG).show()
            }

            Toast.makeText(this, password, Toast.LENGTH_LONG).show()
        }
    }

    private fun updateRecord() {
        val stdList = userSQLiteHelper.getAllRecords()
        val std = stdList[0]
        if (nameEditText.text.isEmpty() || ageEditText.text.isEmpty() || weightEditText.text.isEmpty() ||
            heightEditText.text.isEmpty() || passwordEditText.text.isEmpty()
        ) {
            Toast.makeText(this, "Please enter the required field", Toast.LENGTH_LONG).show()
        } else {
            if (std == null) return
            val name = nameEditText.text.toString()
            val age = ageEditText.text.toString().toInt()
            val weight = weightEditText.text.toString().toFloat()
            val height = heightEditText.text.toString().toFloat()
            val password = passwordEditText.text.toString()
                val newstd = UserModel(
                    id = std!!.id,
                    name = name, age = age, height = height, weight = weight,
                    gender = selectedGender, activityLevel = selectedActivity, password = password
                )
                val status = userSQLiteHelper.updateRecord(newstd)
                if (status > -1) {
                    Toast.makeText(this, "Record updated", Toast.LENGTH_SHORT).show()
                    clearEditText()
                }
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

            private fun clearEditText() {
                nameEditText.setText("")
                ageEditText.setText("")
                heightEditText.setText("")
                weightEditText.setText("")
                val genderRadioGroup: RadioGroup = findViewById(R.id.gender_radio_group)
                genderRadioGroup.clearCheck()
                val activityRadioGroup: RadioGroup = findViewById(R.id.activity_radio_group)
                activityRadioGroup.clearCheck()
                passwordEditText.setText("")
                nameEditText.requestFocus()
            }
        }