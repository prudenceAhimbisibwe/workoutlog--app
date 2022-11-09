package com.prudence.workoutlog.repository

import androidx.lifecycle.LiveData
import com.prudence.workoutlog.Database.ExerciseCategoryDao
import com.prudence.workoutlog.Database.ExerciseDao
import com.prudence.workoutlog.Database.WorkoutDb
import com.prudence.workoutlog.WorkoutLog
import com.prudence.workoutlog.api.ApiClient
import com.prudence.workoutlog.api.ApiInterface
import com.prudence.workoutlog.models.Exercise
import com.prudence.workoutlog.models.ExerciseCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ExerciseRepository {
    val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
    val database = WorkoutDb.getDatabase(WorkoutLog.appContext)
    val exerciseCategoryDao = database.exerciseCategoryDao()
    val exerciseDao = database.exerciseDao()

    suspend fun  fetchExerciseCategories(accessToken: String):Response<List<ExerciseCategory>>{
        return withContext(Dispatchers.IO) {
            var response = apiClient.fetchExerciseCategories(accessToken)
            if (response.isSuccessful){
                var categories = response.body()
                categories?.forEach{ category->
                    exerciseCategoryDao.insertExerciseCategory(category)
                }
            }
            response
        }
    }
    suspend fun fetchExercises(accessToken: String):Response<List<Exercise>> {
        return withContext(Dispatchers.IO) {
            var response = apiClient.fetchExercises(accessToken)
            if (response.isSuccessful){
                var exercises = response.body()
                exercises?.forEach{ exercise->
                    exerciseDao.insertExercise(exercise)
                }
            }
            response
        }
    }

    fun getDbCategories (): LiveData<List<ExerciseCategory>> {
        return  exerciseCategoryDao.getExerciseCategories()
    }

    fun getDbExercises (): LiveData<List<Exercise>> {
        return  exerciseDao.getExercises()
    }
    fun getExerciseCategoryId (categoryId: String): LiveData<List<Exercise>>{
        return exerciseDao.getExercisesByCategoryId(categoryId)
    }
    fun getExercisesByExerciseIds(exerciseIds:List<String>):LiveData<List<Exercise>>{
        return exerciseDao.getTodayExerciseIds(exerciseIds)
    }
}