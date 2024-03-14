package com.example.elec4848_sdp.views.function_views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.elec4848_sdp.R
import com.example.elec4848_sdp.databinding.ActivityExercisetrainingBinding
import np.com.susanthapa.curved_bottom_navigation.CbnMenuItem

import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.elec4848_sdp.views.main_views.MainMenu


/**
 * Main Activity and entry point for the app.
 */
class ExerciseTraining : AppCompatActivity() {

    private lateinit var binding: ActivityExercisetrainingBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_exercisetraining)
//



//        val exerciseStartButton: Button = findViewById(R.id.startExerciseButton)
//        exerciseStartButton.setOnClickListener {
//            addFragment(PlanStepOneFragment(), false, "one")
//        }




        binding = ActivityExercisetrainingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val toolbar = findViewById<Toolbar>(R.id.training_toolbar)
        setSupportActionBar(toolbar)

        val returnButton = toolbar.findViewById(R.id.returnButton) as ImageButton
        returnButton.setOnClickListener {
            changeActivity("return")
        }

        val textView = toolbar.findViewById(R.id.toolbar_name) as TextView
        textView.text = "Exercise Training"


        // Get the navigation host fragment from this Activity
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        // Instantiate the navController using the NavHostFragment
        navController = navHostFragment.navController


        val menuItems = arrayOf(
            CbnMenuItem(
                R.drawable.home, // the icon
                R.drawable.avd_home, // the AVD that will be shown in FAB
                R.id.homeFragment // Jetpack Navigation
            ),
            CbnMenuItem(
                R.drawable.workout,
                R.drawable.avd_workout,
                R.id.workoutFragment
            ),
//            CbnMenuItem(
//                R.drawable.plan,
//                R.drawable.avd_plan,
//                R.id.planStepOneFragment
//            ),
//            CbnMenuItem(
//                R.drawable.profile,
//                R.drawable.avd_profile,
//                R.id.profileFragment
//            )
        )
        binding.navView.setMenuItems(menuItems, 0)
        binding.navView.setupWithNavController(navController)
        Toast.makeText(this, "Exercise Training", Toast.LENGTH_LONG).show()
        }


    private fun changeActivity(string:String){
        when (string) {
            "return" -> {
                val intent = Intent(this, MainMenu::class.java)
                startActivity(intent)
            }
        }
    }

    /**
     * Enables back button support. Simply navigates one element up on the stack.
     */
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    companion object {
        var workoutResultData: String? = null
        var workoutTimer: String? = null
    }


    /**
     * This method is used to increase the notification sound volume to max
     */
//    private fun increaseNotificationVolume() {
//        // Increase the volume to max.
//        val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
//        audioManager.setStreamVolume(
//            AudioManager.STREAM_NOTIFICATION,
//            audioManager.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION),
//            0
//        )
//    }
}