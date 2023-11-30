package com.example.comp3330_project

class UserModel(
    var id: Int = getAutoId(),
    var name: String,
    var age: Int,
    var height: Float,
    var weight: Float,
    var gender: Int,
    var activityLevel: Int,
    var password: String?
) {
    companion object {
        fun getAutoId(): Int {
            val random = java.util.Random()
            return random.nextInt(100)
        }

    }
}

