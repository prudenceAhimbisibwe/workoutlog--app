package com.prudence.workoutlog.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prudence.workoutlog.models.WorkoutLogRecord
import com.prudence.workoutlog.repository.WorkoutLogRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class WorkoutLogRecordViewModel: ViewModel() {
    val workoutLogRepository = WorkoutLogRepository()
    lateinit var todayRecordsLiveData: LiveData<List<WorkoutLogRecord>>

    fun saveWorkoutLogRecord(workoutLogRecord: WorkoutLogRecord){
        viewModelScope.launch {
            workoutLogRepository.saveWorkoutLogRecord(workoutLogRecord)
        }
    }

    fun getTodayWorkoutRecords(userId:String){
        todayRecordsLiveData = workoutLogRepository.getTodaysWorkoutLogRecords(userId,getCurrentDate())
    }


    fun getCurrentDate(): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        return formatter.format(Date())
    }
}