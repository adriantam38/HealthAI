package com.example.comp3330_project

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.Exception

class UserSQLiteHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "User.db"
        private var ID = "id"
        private const val TBL_USER = "tbl_user"
        private const val NAME = "name"
        private const val AGE = "age"
        private const val HEIGHT = "height"
        private const val WEIGHT = "weight"
        private const val GENDER = "gender"
        private const val ACTIVITYLEVEL = "activityLevel"
        private const val PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblUser = ("CREATE TABLE" + TBL_USER + "(" +
                ID + "INTEGER PRIMARY KEY," + NAME + "TEXT," +
                AGE + "INTEGER," + HEIGHT + "REAL," + WEIGHT + "REAL," +
                GENDER + "INTEGER," + ACTIVITYLEVEL + "INTEGER," + PASSWORD + "TEXT" + ")")


        db?.execSQL(createTblUser)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_USER")
        onCreate(db)
    }

    fun insertRecord(std: UserModel): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(NAME, std.name)
        contentValues.put(AGE, std.age)
        contentValues.put(HEIGHT, std.height)
        contentValues.put(WEIGHT, std.weight)
        contentValues.put(GENDER, std.gender)
        contentValues.put(ACTIVITYLEVEL, std.activityLevel)
        contentValues.put(PASSWORD, std.password)

        val success = db.insert(TBL_USER, null, contentValues)
        db.close()
        return success
    }

    fun getAllRecords(): ArrayList<UserModel> {
        val stdList: ArrayList<UserModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_USER"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var name: String
        var age: Int
        var height: Float
        var weight: Float
        var gender: Int
        var activityLevel: Int
        var password: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                age = cursor.getInt(cursor.getColumnIndexOrThrow("age"))
                height = cursor.getFloat(cursor.getColumnIndexOrThrow("height"))
                weight = cursor.getFloat(cursor.getColumnIndexOrThrow("weight"))
                gender = cursor.getInt(cursor.getColumnIndexOrThrow("gender"))
                activityLevel = cursor.getInt(cursor.getColumnIndexOrThrow("activityLevel"))
                password = cursor.getString(cursor.getColumnIndexOrThrow("password"))

                val std = UserModel(
                    id = id, name = name, age = age, height = height, weight = weight,
                    gender = gender, activityLevel = activityLevel, password = password
                )
                stdList.add(std)
            } while (cursor.moveToNext())
        }

        return stdList
    }

    fun getUserID(): Int {
        var userID = 0
        val selectQuery = "SELECT * FROM $TBL_USER"
        val db = this.readableDatabase
        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(selectQuery)
            return 0
        }

        var id: Int

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                userID = id
            } while (cursor.moveToNext())
        }
        return userID
    }
}