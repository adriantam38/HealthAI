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

    private lateinit var returnButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val toolbar = findViewById<Toolbar>(R.id.login_toolbar)
        setSupportActionBar(toolbar)

        val textView = toolbar.findViewById(R.id.toolbar_name) as TextView
        textView.text = "Login"

        val returnButton = toolbar.findViewById(R.id.returnButton) as ImageButton
        returnButton.setOnClickListener {
            changeActivity("Activity")
        }


        val saveButton: Button = findViewById(R.id.LoginButton)
        saveButton.setOnClickListener{
            verifyPassword()
        }
    }

    private fun initReturnButton(){
        val resID: Int = resources.getIdentifier("back_arrow.png", "drawable", "com.example.comp3330_project")
    }

    private fun verifyPassword() {

        val sharedPref = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        val password = sharedPref.getString("password", null)
        passwordEditText = findViewById(R.id.PasswordEditText)
        val inputPassword = passwordEditText.text.toString()

        if (inputPassword == password) {
            loadProfile()
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
            changeActivity("Menu")
        }
        else{
            Toast.makeText(this, password, Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadProfile() {
        val sharedPref = getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        val age = sharedPref.getString("Age", null)
        val gender = sharedPref.getInt("Gender", 0)
        val weight = sharedPref.getFloat("Weight", 0.0F)
        val height = sharedPref.getFloat("Height", 0.0F)
        val activityLevel = sharedPref.getInt("ActivityLevel", 0)
    }

    private fun changeActivity(string: String){
        if (string == "Activity"){
            val intent = Intent(this@Login, MainActivity::class.java)
            startActivity(intent)
        }
        else if (string == "Menu"){
            val intent = Intent(this@Login, MainMenu::class.java)
            startActivity(intent)
        }
    }
}