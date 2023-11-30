package com.example.comp3330_project

import android.content.SharedPreferences
import android.util.Log

class User(
    private var age: Int,
    private var height: Float,
    private var weight: Float,
    private var gender: Int,
    private var activityLevel: Int,
    private var password: String?
){
    init {
        Log.v("UserClass", "Profile Created")
    }
}

