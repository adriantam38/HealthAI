package com.example.elec4848_sdp.functions.trackers.calories

class CaloriesModel (
    var id: Int = getAutoId(),
    var userID: Int,
    var name: String,
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