package com.prudence.workoutlog.repository

import com.prudence.workoutlog.Database.WorkoutDb
import com.prudence.workoutlog.WorkoutLog
import com.prudence.workoutlog.api.ApiClient
import com.prudence.workoutlog.api.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExerciseRepository {
    val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
    val database = WorkoutDb.getDatabase(WorkoutLog.appContext)
    val exerciseCategoryDao = database.ExerciseCategoryDao()
    suspend fun fetchExerciseCategory(accessToken: String) =
        withContext(Dispatchers.IO) {
        var response = apiClient.fetchExerciseCategory(accessToken)
            if (response.isSuccessful){
                var category = response.body()
                category?.forEach{ category->
                    exerciseCategoryDao.insertExercise(category)
                }
            }
            response
    }
}