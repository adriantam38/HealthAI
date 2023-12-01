
/*Main Menu page*/
package com.example.comp3330_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

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
    }

    private fun changeActivity(str: String){
        if (str == "Login"){
            val intent = Intent(this, Login::class.java)
            startActivity(intent)}
        else if (str == "CreateProfile"){
            val intent = Intent(this, CreateProfile::class.java)
            intent.putExtra("mode", "Create")
            startActivity(intent)}

    }

}