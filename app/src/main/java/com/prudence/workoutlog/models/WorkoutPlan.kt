package com.prudence.workoutlog.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "WorkoutPlan")
data class WorkoutPlan(
    @PrimaryKey var workoutPlanId : String,
    var userId: String
)
