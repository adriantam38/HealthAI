package com.example.elec4848_sdp.functions.exercisetraining.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.elec4848_sdp.functions.exercisetraining.data.plan.Plan
import com.example.elec4848_sdp.functions.exercisetraining.data.plan.PlanDataDao
import com.example.elec4848_sdp.functions.exercisetraining.data.results.DateConverters
import com.example.elec4848_sdp.functions.exercisetraining.data.results.WorkoutResult
import com.example.elec4848_sdp.functions.exercisetraining.data.results.WorkoutResultDao

@Database(
    entities = [Plan::class, WorkoutResult::class],
    version = 7,
    exportSchema = false
)
@TypeConverters(DateConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun planDao(): PlanDataDao
    abstract fun resultDao(): WorkoutResultDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "repdetect_database.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}