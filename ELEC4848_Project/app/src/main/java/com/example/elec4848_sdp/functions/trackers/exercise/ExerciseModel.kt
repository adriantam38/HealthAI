package com.example.elec4848_sdp.functions.trackers.exercise

class ExerciseModel (
    var id: Int = getAutoId(),
    var userID: Int,
    var name: String,
    var intensity: String,
    var duration: Int,
    var calories: Int,
    var date: String,
    var time: String
) {
    companion object {
        fun getAutoId(): Int {
            val random = java.util.Random()
            return random.nextInt(100)
        }
    }
}