package com.example.elec4848_sdp.functions.trackers.exercise

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.elec4848_sdp.functions.trackers.calories.CaloriesModel
import com.example.elec4848_sdp.functions.trackers.calories.CaloriesSQLiteHelper
import java.lang.Exception

class ExerciseSQLiteHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "exercise.db"
        private const val ID = "id"
        private const val TBL_EXERCISE = "tbl_exercise"
        private const val USERID = "userID"
        private const val NAME = "name"
        private const val INTENSITY = "intensity"
        private const val DURATION = "duration"
        private const val CALORIES = "calories"
        private const val DATE = "date"
        private const val TIME = "time"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTbl = ("CREATE TABLE " + TBL_EXERCISE + "(" +
                ID + " INTEGER PRIMARY KEY, " + USERID + " INTEGER, " +
                NAME + " TEXT, " + CALORIES + " INTEGER, " + INTENSITY + " TEXT, " +
                DURATION + " INTEGER, " + DATE + " TEXT, " +
                TIME + " TEXT" +")")
        db?.execSQL(createTbl)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_EXERCISE")
        onCreate(db)
    }

    fun insertRecord(std: ExerciseModel): Long{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(USERID, std.userID)
        contentValues.put(NAME, std.name)
        contentValues.put(INTENSITY, std.intensity)
        contentValues.put(DURATION, std.duration)
        contentValues.put(CALORIES, std.calories)
        contentValues.put(DATE, std.date)
        contentValues.put(TIME, std.time)

        val success = db.insert(TBL_EXERCISE, null, contentValues)
        db.close()
        return success
    }

    fun getAllRecords():ArrayList<ExerciseModel> {
        val stdList: ArrayList<ExerciseModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_EXERCISE"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var userID: Int
        var name: String
        var intensity: String
        var duration: Int
        var calories: Int
        var date: String
        var time: String

        if (cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                userID = cursor.getInt(cursor.getColumnIndexOrThrow("userID"))
                name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                intensity = cursor.getString(cursor.getColumnIndexOrThrow("intensity"))
                duration = cursor.getInt(cursor.getColumnIndexOrThrow(("duration")))
                calories = cursor.getInt(cursor.getColumnIndexOrThrow("calories"))
                date = cursor.getString(cursor.getColumnIndexOrThrow("date"))
                time = cursor.getString(cursor.getColumnIndexOrThrow("time"))

                val std = ExerciseModel(id = id, userID = userID, name = name, intensity = intensity, duration = duration, calories = calories, date = date, time = time)
                stdList.add(std)
            }while (cursor.moveToNext())
        }
        cursor.close()
        return stdList
    }

    fun updateRecords(std: CaloriesModel):Int{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(USERID, std.userID)
        contentValues.put(NAME, std.name)
        contentValues.put(CALORIES, std.calories)
        contentValues.put(DATE, std.date)
        contentValues.put(TIME, std.time)

        val success = db.update(TBL_EXERCISE, contentValues, "id=" + std.id, null)
        db.close()
        return success
    }

    fun deleteRecordsById(id:Int): Int{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, id)

        val success = db.delete(TBL_EXERCISE, "id=$id", null)
        db.close()
        return success
    }
}