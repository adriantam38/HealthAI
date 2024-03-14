
/*Main Menu page*/
package com.example.elec4848_sdp.views.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.elec4848_sdp.R
import com.example.elec4848_sdp.views.function_views.CreateProfile
import com.example.elec4848_sdp.views.main_views.Login
import com.example.elec4848_sdp.views.main_views.MainMenu

class MainActivity : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val createProfileButton:Button = findViewById(R.id.CreateProfile)
        createProfileButton.setOnClickListener {
            changeActivity("CreateProfile")
        }

        val loginButton:Button = findViewById(R.id.Login)
        loginButton.setOnClickListener {
            changeActivity("Login")
        }

        val guestButton:Button = findViewById(R.id.Guest)
        guestButton.setOnClickListener {
            changeActivity("Guest")
        }
    }

    private fun changeActivity(str: String){
        if (str == "Login"){
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        else if (str == "CreateProfile"){
            val intent = Intent(this, CreateProfile::class.java)
            intent.putExtra("mode", "Create")
            startActivity(intent)
        }
        else if (str == "Guest"){
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent)
        }
    }

}