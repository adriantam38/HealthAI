package com.example.comp3330_project

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class Login: AppCompatActivity() {

    private lateinit var passwordEditText: EditText
    private lateinit var nameEditText: EditText

    private lateinit var returnButton: ImageButton
    private lateinit var saveButton: Button

    private lateinit var userSQLiteHelper: UserSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userSQLiteHelper = UserSQLiteHelper(this)

        val toolbar = findViewById<Toolbar>(R.id.login_toolbar)
        setSupportActionBar(toolbar)

        val textView = toolbar.findViewById(R.id.toolbar_name) as TextView
        textView.text = "Login"

        returnButton = toolbar.findViewById(R.id.returnButton) as ImageButton
        returnButton.setOnClickListener {
            changeActivity("Activity")
        }

        saveButton = findViewById(R.id.LoginButton)
        saveButton.setOnClickListener {
            verifyPassword()
        }
    }

    private fun verifyPassword() {
        nameEditText = findViewById(R.id.ProfileNameEditText)
        passwordEditText = findViewById(R.id.PasswordEditText)

        val inputName = nameEditText.text.toString()
        val inputPassword = passwordEditText.text.toString()
        val name = userSQLiteHelper.getUserName()
        val password = userSQLiteHelper.getUserPassword()
        Toast.makeText(this, password, Toast.LENGTH_LONG).show()
        Toast.makeText(this, inputPassword, Toast.LENGTH_LONG).show()

        if (inputName == name && inputPassword == password) {
            //loadProfile()
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
            changeActivity("Menu")
        }
        else{
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
        }
    }

    /*private fun loadProfile() {
        val sharedPref = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        val age = sharedPref.getString("Age", null)
        val gender = sharedPref.getInt("Gender", 0)
        val weight = sharedPref.getFloat("Weight", 0.0F)
        val height = sharedPref.getFloat("Height", 0.0F)
        val activityLevel = sharedPref.getInt("ActivityLevel", 0)
    }*/

    private fun changeActivity(string: String){
        if (string == "Activity"){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        else if (string == "Menu"){
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent)
        }
    }
}