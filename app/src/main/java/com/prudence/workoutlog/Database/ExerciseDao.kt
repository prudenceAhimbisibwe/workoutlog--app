package com.prudence.workoutlog.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prudence.workoutlog.models.Exercise

@Dao
interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExercise(exercise: Exercise)

    @Query( "SELECT * FROM Exercises")
    fun getExercises (): LiveData<List<Exercise>>

    @Query("SELECT * FROM Exercises WHERE categoryId=  :categoryId ")
    fun getExercisesByCategoryId(categoryId: String): LiveData<List<Exercise>>

    @Query("SELECT * FROM Exercises WHERE  exerciseId IN (:todaysExercisesIds)")
    fun getTodayExerciseIds(todaysExercisesIds: List<String>):LiveData<List<Exercise>>

    }

