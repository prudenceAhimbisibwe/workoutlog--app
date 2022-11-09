package com.prudence.workoutlog.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Index

@Entity(tableName = "WorkoutPlans", indices = [Index(value = ["workoutPlanId","day"], unique = true)])
data class WorkoutPlanItem(
    @PrimaryKey var workoutPlanItemId : String,
    var workoutPlanId : String,
    var day : Int,
    var exerciseId: List<String>
)
