package com.example.comp3330_project

import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CaloriesTracker:AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var caloriesEditText: EditText
    private lateinit var caloriesAddButton: Button

    private lateinit var sqLiteHelper: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caloriestracker)

        nameEditText = findViewById(R.id.nameEditText)
        caloriesEditText = findViewById(R.id.caloriesEditText)
        caloriesAddButton = findViewById(R.id.caloriesAddButton)
        sqLiteHelper = SQLiteHelper(this)

        caloriesAddButton.setOnClickListener { addStudent() }
    }

    private fun addStudent(){
        val name = nameEditText.text.toString()
        val calories = caloriesEditText.text.toString().toIntOrNull()

        if (name.isEmpty() || calories == null){
            Toast.makeText(this, "Please enter the required field", Toast.LENGTH_LONG).show()
        }else {
            val std = CaloriesModel(name = name, calories = calories)
            val status = sqLiteHelper.insertRecord(std)
            if (status > -1){
                Toast.makeText(this, "Record added", Toast.LENGTH_LONG).show()
                clearEditText()
            }
            else {
                Toast.makeText(this,"Record not added", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun clearEditText(){
        nameEditText.setText("")
        caloriesEditText.setText("")
        nameEditText.requestFocus()
    }


}