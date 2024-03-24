package com.example.elec4848_sdp.views.function_views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.elec4848_sdp.functions.trackers.exercise.ExerciseAdapter
import com.example.elec4848_sdp.functions.trackers.exercise.ExerciseSQLiteHelper
import com.example.elec4848_sdp.R
import com.example.elec4848_sdp.views.main_views.MainMenu

class ExerciseRecord: AppCompatActivity(){
    private lateinit var exerciseSqLiteHelper: ExerciseSQLiteHelper

    private lateinit var recyclerView: RecyclerView
    private var adapter: ExerciseAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_activity_calories_record_veiwer)

        val toolbar = findViewById<Toolbar>(R.id.CaloriesView_toolbar)
        setSupportActionBar(toolbar)

        val textView = toolbar.findViewById(R.id.toolbar_name) as TextView
        textView.text = "Exercise Record"

        val returnButton = toolbar.findViewById(R.id.returnButton) as ImageButton
        returnButton.setOnClickListener {
            changeActivity()
        }

        initRecyclerView()
        exerciseSqLiteHelper = ExerciseSQLiteHelper(this)
        getExercise()
        adapter?.setOnClickDeleteItem {
            deleteRecord(it.id)
        }
    }

    private fun getExercise() {
        val stdList = exerciseSqLiteHelper.getAllRecords()
        Log.v("pppp", "${stdList.size}")

        adapter?.addItems(stdList)
    }

    private fun initRecyclerView(){
        recyclerView = findViewById(R.id.RecordRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ExerciseAdapter()
        recyclerView.adapter = adapter
    }

    private fun deleteRecord( id:Int ){
        if (id == null) return

        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to delete this item?")
        builder.setCancelable(true)
        builder.setPositiveButton("Yes"){
            dialog, _ ->dialog.dismiss()
            exerciseSqLiteHelper.deleteRecordsById(id)
            getExercise()
        }
        builder.setNegativeButton("No"){
            dialog, _ -> dialog.dismiss()
        }
        val alert = builder.create()
        alert.show()
    }

    private fun changeActivity() {
        val intent = Intent(this, MainMenu::class.java)
        startActivity(intent)
    }
}