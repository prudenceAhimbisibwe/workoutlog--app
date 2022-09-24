package com.prudence.workoutlog.repository

import com.prudence.workoutlog.api.ApiClient
import com.prudence.workoutlog.api.ApiInterface
import com.prudence.workoutlog.models.LoginRequest
import com.prudence.workoutlog.models.LoginResponse
import com.prudence.workoutlog.models.RegisterRequest
import com.prudence.workoutlog.models.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    val apiClient= ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun loginUser(loginRequest: LoginRequest)
    = withContext(Dispatchers.IO){
        val response = apiClient.loginUser(loginRequest)
        return@withContext response
    }
    suspend fun registerUser(registerRequest: RegisterRequest)
    = withContext(Dispatchers.IO) {
        val response = apiClient.registerUser(registerRequest)
        return@withContext response
    }
}
