package com.example.elec4848_sdp.functions.exercisetraining.data.database

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.elec4848_sdp.functions.exercisetraining.data.results.WorkoutResult
import com.example.elec4848_sdp.functions.exercisetraining.data.results.WorkoutResultDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/**
Repository class for handling data operations
 **/
class AppRepository(application: Application) : CoroutineScope {
    // Coroutine context for background tasks
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    // Data Access Objects for plans and workout results
    private var resultDao: WorkoutResultDao?

    // Initialize DAOs using the application's database
    init {
        val database = AppDatabase.getDatabase(application)
        resultDao = database.resultDao()
    }

    // Insert a workout result into the database
    suspend fun insertResult(result: WorkoutResult) {
        resultDao?.insert(result)
    }

    // Get the most recent workout results
    suspend fun getRecentWorkout(): List<WorkoutResult>? = resultDao?.getRecentWorkout()

}