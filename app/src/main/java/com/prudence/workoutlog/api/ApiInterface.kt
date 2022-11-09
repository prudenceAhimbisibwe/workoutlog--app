package com.prudence.workoutlog.api

import com.prudence.workoutlog.models.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header


interface ApiInterface {
    @POST("/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

    @POST("/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("/exerciseCategories")
    suspend fun fetchExerciseCategories(@Header("Authorization")accessToken:String): Response<List<ExerciseCategory>>

    @GET("/exercises")
    suspend fun fetchExercises(@Header("Authorization")accessToken:String): Response<List<Exercise>>

    @POST ("/profile")
    suspend fun createProfile (@Body ProfileRequest : ProfileRequest) : Response<ProfileResponse>
}
