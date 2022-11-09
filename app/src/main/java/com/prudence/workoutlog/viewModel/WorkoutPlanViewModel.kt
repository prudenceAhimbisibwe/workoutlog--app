package com.prudence.workoutlog.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prudence.workoutlog.models.WorkoutPlan
import com.prudence.workoutlog.models.WorkoutPlanItem
import com.prudence.workoutlog.repository.WorkoutPlanRepository
import kotlinx.coroutines.launch
import java.util.*

class WorkoutPlanViewModel:ViewModel() {
    val workoutPlanRepository = WorkoutPlanRepository()
    lateinit var  workoutPlanLivedata: LiveData<WorkoutPlan>
    var selectedExerciseIds = mutableListOf<String>()

    fun saveWorkoutPlan(workoutPlan: WorkoutPlan){
        viewModelScope.launch {
            workoutPlanRepository.saveWorkoutPlan(workoutPlan)
        }
    }

    fun getExistingWorkoutPlans(userId: String){
        workoutPlanLivedata = workoutPlanRepository.getWorkoutPlanByUserId(userId)
    }

    fun createWorkoutPlanItem (dayNumber: Int , workoutPlanId : String){
        val workoutPlanItem  = WorkoutPlanItem(
            workoutPlanItemId = UUID.randomUUID().toString(),
            workoutPlanId = workoutPlanId,
            day = dayNumber,
            exerciseId = selectedExerciseIds
        )
        viewModelScope.launch {
            workoutPlanRepository.saveWorkoutPlanItem(workoutPlanItem)
        }
    }

    fun getTodayWorkoutPlanItem(workoutPlanId: String, dayNumber: Int): LiveData<WorkoutPlanItem> {
        return  workoutPlanRepository.getTodayWorkoutPlanItem(workoutPlanId,dayNumber)
    }
}
