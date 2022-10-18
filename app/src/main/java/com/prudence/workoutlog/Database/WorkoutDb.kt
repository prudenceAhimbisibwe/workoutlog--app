package com.prudence.workoutlog.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.prudence.workoutlog.models.ExerciseCategory


@Database( entities = arrayOf(ExerciseCategory::class), version = 1)
abstract class WorkoutDb : RoomDatabase() {
    abstract fun ExerciseCategoryDao(): ExerciseCategoryDao

    companion object{
        private var database: WorkoutDb?=null
        fun getDatabase(context:Context):WorkoutDb {
            if (database == null) {
                database = Room
                    .databaseBuilder(context, WorkoutDb::class.java, "WorkoutDb")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return database as WorkoutDb
        }
    }
}
