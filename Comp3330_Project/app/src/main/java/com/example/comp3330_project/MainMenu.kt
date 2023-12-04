package com.example.comp3330_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

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

        val stepCountButton: Button = findViewById(R.id.stepCounterButton)
        stepCountButton.setOnClickListener{
            changeActivity("stepTracker")
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
            "stepTracker" -> {
                val intent = Intent(this, StepTracker::class.java) //change CaloriesTracker later
                startActivity(intent)
            }
            "heartbeat" ->{
                val intent = Intent(this, HeartBeatMain::class.java)
                startActivity(intent)
            }
            "setting"->{
                val intent = Intent(this, CreateProfile::class.java) //change CaloriesTracker later
                intent.putExtra("mode", "Modify")
                startActivity(intent)
            }
            "test_calories"->{
                val intent = Intent(this, Test_CaloriesTrackerView::class.java)
                startActivity(intent)
            }
            "test_user"->{
                val intent = Intent(this, Test_UserView::class.java)
                startActivity(intent)
            }
            "test_exercise"->{
                val intent = Intent(this, Test_ExerciseTrackerView::class.java)
                startActivity(intent)
            }
        }
    }
}