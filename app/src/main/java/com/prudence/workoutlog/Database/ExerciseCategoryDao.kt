package com.prudence.workoutlog.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prudence.workoutlog.models.ExerciseCategory

@Dao
interface ExerciseCategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExercise(exerciseCategory: ExerciseCategory)


}