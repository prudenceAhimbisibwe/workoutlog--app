package com.prudence.workoutlog.repository

import androidx.lifecycle.LiveData
import com.prudence.workoutlog.Database.WorkoutDb
import com.prudence.workoutlog.WorkoutLog
import com.prudence.workoutlog.models.WorkoutLogRecord
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WorkoutLogRepository {
    val database = WorkoutDb.getDatabase(WorkoutLog.appContext)
    val workoutLogRecordDao = database.WorkoutLogRecordDaoDao()

    suspend fun saveWorkoutLogRecord(workoutLogRecord: WorkoutLogRecord){
        withContext(Dispatchers.IO){
            workoutLogRecordDao.insertWorkoutLogRecord(workoutLogRecord)
        }
    }

    fun getTodaysWorkoutLogRecords(userId:String, currentDate: String):
            LiveData<List<WorkoutLogRecord>> {
        return  workoutLogRecordDao.getWorkoutLogsByuserId(userId, currentDate)
    }
}
