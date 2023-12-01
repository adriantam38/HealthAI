package com.example.comp3330_project

class CaloriesModel (
    var id: Int = getAutoId(),
    var userID: Int,
    var name: String,
    var calories: Int
) {
    companion object {
        fun getAutoId(): Int {
            val random = java.util.Random()
            return random.nextInt(100)
        }
    }
}