package com.example.comp3330_project

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.Exception

class SQLiteHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "User.db"
        private const val ID = "id"
        private const val TBL_CALORIES = "tbl_calories"
        private const val NAME = "name"
        private const val CALORIES = "calories"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblUser = ("CREATE TABLE" + TBL_CALORIES + "(" +
                                    ID + "INTEGER PRIMARY KEY," + NAME + "TEXT," +
                                    CALORIES + "INTEGER, " +")")
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
        var name: String
        var calories: Int

        if (cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                name = cursor.getString(cursor.getColumnIndex("name"))
                calories = cursor.getInt(cursor.getColumnIndex("calories"))

                val std = CaloriesModel(id = id, name = name, calories = calories)
                stdList.add(std)
            }while (cursor.moveToNext())
        }

        return stdList
    }
}