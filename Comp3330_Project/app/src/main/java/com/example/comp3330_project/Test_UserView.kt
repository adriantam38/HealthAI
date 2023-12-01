package com.example.comp3330_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class Test_UserView:AppCompatActivity() {
    private lateinit var userSQLiteHelper: UserSQLiteHelper
    private lateinit var id: TextView
    private lateinit var name: TextView
    private lateinit var age: TextView
    private lateinit var height: TextView
    private lateinit var weight: TextView
    private lateinit var gender: TextView
    private lateinit var activityLevel: TextView
    private lateinit var password: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_activity_user_record_viewer)


        val toolbar = findViewById<Toolbar>(R.id.UserView_toolbar)
        setSupportActionBar(toolbar)

        val returnButton = toolbar.findViewById(R.id.returnButton) as ImageButton
        returnButton.setOnClickListener {
            changeActivity()
        }

        val viewButton: Button = findViewById(R.id.viewButton)
        viewButton.setOnClickListener { getUser() }
        userSQLiteHelper = UserSQLiteHelper(this)

        id = findViewById(R.id.RecordID)
        name = findViewById(R.id.Name)
        age = findViewById(R.id.age)
        height = findViewById(R.id.height)
        weight = findViewById(R.id.weight)
        gender = findViewById(R.id.gender)
        activityLevel = findViewById(R.id.activityLevel)
        password = findViewById(R.id.password)
    }

    private fun getUser(){
        val stdList = userSQLiteHelper.getAllRecords()
        val std = stdList[0]

        id.text = std.id.toString()
        name.text = std.name
        age.text = std.age.toString()
        height.text = std.height.toString()
        weight.text = std.weight.toString()
        gender.text = std.gender.toString()
        activityLevel.text = std.activityLevel.toString()
        password.text = std.password
    }

    private fun changeActivity() {
        val intent = Intent(this, MainMenu::class.java)
        startActivity(intent)
    }
}