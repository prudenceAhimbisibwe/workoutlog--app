package com.prudence.workoutlog.api

import com.prudence.workoutlog.models.LoginRequest
import com.prudence.workoutlog.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

import com.prudence.workoutlog.models.RegisterRequest
import com.prudence.workoutlog.models.RegisterResponse
import retrofit2.Response


interface ApiInterface {
    @POST("/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

    @POST("/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest): Response<LoginResponse>
}
