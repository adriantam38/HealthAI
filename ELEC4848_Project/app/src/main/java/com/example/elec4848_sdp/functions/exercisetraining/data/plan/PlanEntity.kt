package com.example.elec4848_sdp.functions.exercisetraining.data.plan

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity class representing a plan item stored in the database
 */
@Entity(tableName = "plan_items")
data class Plan(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val exercise: String,
    val calories: Double,
    val repeatCount: Int,
    val selectedDays: String,
    var completed: Boolean = false,
    val timeCompleted: Long? = null
)




