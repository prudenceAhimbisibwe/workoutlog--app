package com.prudence.workoutlog.repository

import com.prudence.workoutlog.api.ApiClient
import com.prudence.workoutlog.api.ApiInterface
import com.prudence.workoutlog.models.ProfileRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfileRepository {
    val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun create (createProfilerequest : ProfileRequest)
            = withContext(Dispatchers.IO){
        val response = apiClient.createProfile(createProfilerequest)
        return@withContext response
    }

}