package com.prudence.workoutlog.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prudence.workoutlog.models.WorkoutPlanItem

@Dao
interface WorkoutPlanItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkoutPlanItem (workoutPlanItem: WorkoutPlanItem)

    @Query("SELECT * FROM WorkoutPlans WHERE workoutPlanId= :workoutPlanId AND day = :dayNumber")
    fun getTodayWorkoutPlanItem(workoutPlanId:String, dayNumber:Int): LiveData<WorkoutPlanItem>
}