package com.prudence.workoutlog.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prudence.workoutlog.models.WorkoutLogRecord

@Dao
interface WorkoutLogRecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkoutLogRecord(workoutLogRecord: WorkoutLogRecord)

    @Query("SELECT * FROM WorkoutLogRecord WHERE userId = :userId AND date >= :currentDate")
    fun getWorkoutLogsByuserId(userId: String, currentDate : String): LiveData<List<WorkoutLogRecord>>

}