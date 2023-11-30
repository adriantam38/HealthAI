package com.example.comp3330_project

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast

object UserCreator {
    fun getUser(context: Context): User {
        val prefs: SharedPreferences = context.getSharedPreferences("Name", Context.MODE_PRIVATE)

        //Check if the user is already stored, if is, then simply get the data from
        //your SharedPreference object.
        val isValid: Boolean = prefs.getBoolean("valid", false)
        return if (isValid) {
            val age: Int = prefs.getInt("age", 0)
            val height: Float = prefs.getFloat("height", 0.0F)
            val weight: Float = prefs.getFloat("weight", 0.0F)
            val gender: Int = prefs.getInt("gender", 0)
            val activityLevel: Int = prefs.getInt("activityLevel", 0)
            val password: String? = prefs.getString("password", "")

            User(age, height, weight, gender, activityLevel, password)
        }
        else{
            Toast.makeText(, "invalid", Toast.LENGTH_LONG).show()
        }
    }
    fun addUser(age: Int, height: Float, weight: Float, gender: Int, activityLevel: Int, password: String) {
            //for example show a dialog here, where the user can log in.
            //when you have the data, then:
            if ()
            run {
                val editor: SharedPreferences.Editor = prefs.edit()
                editor.putString("user", context.getString("age"))
                editor.putString("pass", "somepassword")
                editor.putInt("age", 20)
                editor.putBoolean("valid", true)
                editor.commit()
            }
            getUser(context)
        }
    }
}