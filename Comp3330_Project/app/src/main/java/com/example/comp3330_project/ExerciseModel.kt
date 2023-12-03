package com.example.comp3330_project

class ExerciseModel (
    var id: Int = getAutoId(),
    var userID: Int,
    var name: String,
    var intensity: String,
    var duration: Int,
    var calories: Int
) {
    companion object {
        fun getAutoId(): Int {
            val random = java.util.Random()
            return random.nextInt(100)
        }
    }
}