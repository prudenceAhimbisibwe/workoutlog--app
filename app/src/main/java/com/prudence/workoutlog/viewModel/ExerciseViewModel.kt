package com.prudence.workoutlog.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prudence.workoutlog.models.Exercise
import com.prudence.workoutlog.models.ExerciseCategory
import com.prudence.workoutlog.repository.ExerciseRepository
import kotlinx.coroutines.launch

class ExerciseViewModel: ViewModel(){
    val exerciseRepository = ExerciseRepository()
    lateinit var exerciseCategoryLiveData : LiveData<List<ExerciseCategory>>
    lateinit var  exerciseLiveData : LiveData<List<Exercise>>

    val errorLiveData = MutableLiveData<String?>()

    fun fetchExerciseCategories(accessToken: String) {
        viewModelScope.launch {
            val response = exerciseRepository.fetchExerciseCategories(accessToken)
            if (response.isSuccessful) {
                errorLiveData.postValue(response.body()?.toString())
            }
        }
    }

    fun fetchExercises(accessToken: String) {
        viewModelScope.launch {
            val response = exerciseRepository.fetchExercises(accessToken)

        }
    }
    fun fetchDbCategories(){
        exerciseCategoryLiveData = exerciseRepository.getDbCategories()
    }

    fun fetchDbExercises(){
        exerciseLiveData = exerciseRepository.getDbExercises()
    }
    fun getExerciseByCategoryId (categoryId: String){
        exerciseLiveData = exerciseRepository.getExerciseCategoryId(categoryId)
    }

    fun getExercisesIds (exerciseIds : List<String>): LiveData<List<Exercise>> {
        return  exerciseRepository.getExercisesByExerciseIds(exerciseIds)
    }
}