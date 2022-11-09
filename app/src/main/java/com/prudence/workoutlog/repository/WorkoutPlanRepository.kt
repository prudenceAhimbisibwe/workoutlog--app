package com.prudence.workoutlog.repository

import androidx.lifecycle.LiveData
import com.prudence.workoutlog.Database.WorkoutDb
import com.prudence.workoutlog.WorkoutLog
import com.prudence.workoutlog.models.WorkoutPlan
import com.prudence.workoutlog.models.WorkoutPlanItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WorkoutPlanRepository {
    val database = WorkoutDb.getDatabase(WorkoutLog.appContext)
    val workoutPlanDao = database.WorkoutPlanDao()
    val workPlanItemDao = database.WorkPlanItemDao()

    suspend fun saveWorkoutPlan (workoutPlan: WorkoutPlan){
        withContext(Dispatchers.IO){
            workoutPlanDao.insertWorkoutPlan(workoutPlan)
        }
    }
    suspend fun saveWorkoutPlanItem (workoutPlanItem: WorkoutPlanItem){
        withContext(Dispatchers.IO){
            workPlanItemDao.insertWorkoutPlanItem(workoutPlanItem)
        }
    }
    fun getWorkoutPlanByUserId (userId: String): LiveData<WorkoutPlan> {
        return  workoutPlanDao.getWorkoutPlanByUserId(userId)
    }

    fun getTodayWorkoutPlanItem (workoutPlanId:String, dayNumber:Int):LiveData<WorkoutPlanItem>{
        return workPlanItemDao.getTodayWorkoutPlanItem(workoutPlanId,dayNumber)
    }
}
