package com.example.comp3330_project

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.Exception

class CaloriesSQLiteHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "calories.db"
        private const val ID = "id"
        private const val TBL_CALORIES = "tbl_calories"
        private const val USERID = "userID"
        private const val NAME = "name"
        private const val CALORIES = "calories"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblUser = ("CREATE TABLE " + TBL_CALORIES + "(" +
                ID + " INTEGER PRIMARY KEY, " + USERID + " INTEGER, " +
                NAME + " TEXT, " + CALORIES + " INTEGER" +")")
        db?.execSQL(createTblUser)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_CALORIES")
        onCreate(db)
    }

    fun insertRecord(std:CaloriesModel): Long{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(USERID, std.userID)
        contentValues.put(NAME, std.name)
        contentValues.put(CALORIES, std.calories)

        val success = db.insert(TBL_CALORIES, null, contentValues)
        db.close()
        return success
    }

    fun getAllRecords():ArrayList<CaloriesModel> {
        val stdList: ArrayList<CaloriesModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_CALORIES"
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
        var calories: Int

        if (cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                userID = cursor.getInt(cursor.getColumnIndexOrThrow("userID"))
                name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                calories = cursor.getInt(cursor.getColumnIndexOrThrow("calories"))

                val std = CaloriesModel(id = id, userID = userID, name = name, calories = calories)
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

        val success = db.update(TBL_CALORIES, contentValues, "id=" + std.id, null)
        db.close()
        return success
    }

    fun deleteRecordsById(id:Int): Int{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, id)

        val success = db.delete(TBL_CALORIES, "id=$id", null)
        db.close()
        return success
    }
}