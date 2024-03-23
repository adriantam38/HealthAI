package com.example.elec4848_sdp.views.main_views

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.elec4848_sdp.R
import com.example.elec4848_sdp.views.function_views.CaloriesTracker
import com.example.elec4848_sdp.views.function_views.CaloriesRecord
import com.example.elec4848_sdp.views.function_views.CreateProfile
import com.example.elec4848_sdp.views.function_views.ExerciseTracker
import com.example.elec4848_sdp.views.function_views.ExerciseRecord
import com.example.elec4848_sdp.views.function_views.PulseDetector
import com.example.elec4848_sdp.views.function_views.UserSettings
import com.example.elec4848_sdp.views.function_views.ExerciseTraining

class MainMenu: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainmenu)

        val caloriesTrackerButton: Button = findViewById(R.id.caloriesTrackerButton)
        caloriesTrackerButton.setOnClickListener{
            changeActivity("caloriesTracker")
        }

        val exerciseTrackButton: Button = findViewById(R.id.exerciseTrackerButton)
        exerciseTrackButton.setOnClickListener{
            changeActivity("exerciseTracker")
        }

        val heartRateButton: Button = findViewById(R.id.heartRateButton)
        heartRateButton.setOnClickListener{
            changeActivity("heartbeat")
        }

        val settingButton: Button = findViewById(R.id.settingButton)
        settingButton.setOnClickListener {
            changeActivity("setting")
        }

        val testCalories: Button = findViewById(R.id.testCalories)
        testCalories.setOnClickListener {
            changeActivity("test_calories")
        }

        val testUser: Button = findViewById(R.id.testUser)
        testUser.setOnClickListener {
            changeActivity("test_user")
        }

        val testExercise: Button = findViewById(R.id.testExercise)
        testExercise.setOnClickListener{
            changeActivity("test_exercise")
        }

        val exerciseTraining: Button = findViewById(R.id.exerciseTraining)
        exerciseTraining.setOnClickListener{
            changeActivity("exerciseTraining")
        }
    }

    private fun changeActivity(string:String){
        when (string){
            "caloriesTracker" -> {
                val intent = Intent(this, CaloriesTracker::class.java)
                startActivity(intent)
            }
            "exerciseTracker" -> {
                val intent = Intent(this, ExerciseTracker::class.java) //change CaloriesTracker later
                startActivity(intent)
            }
            "heartbeat" ->{
                val intent = Intent(this, PulseDetector::class.java)
                startActivity(intent)
            }
            "setting"->{
                val intent = Intent(this, CreateProfile::class.java) //change CaloriesTracker later
                intent.putExtra("mode", "Modify")
                startActivity(intent)
            }
            "test_calories"->{
                val intent = Intent(this, CaloriesRecord::class.java)
                startActivity(intent)
            }
            "test_user"->{
                val intent = Intent(this, UserSettings::class.java)
                startActivity(intent)
            }
            "test_exercise"->{
                val intent = Intent(this, ExerciseRecord::class.java)
                startActivity(intent)
            }
            "exerciseTraining"->{
                val intent = Intent(this, ExerciseTraining::class.java)
                startActivity(intent)
            }
        }
    }
}