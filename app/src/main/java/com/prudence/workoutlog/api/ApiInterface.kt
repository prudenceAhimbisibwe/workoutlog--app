package com.prudence.workoutlog.api

import com.prudence.workoutlog.models.LoginRequest
import com.prudence.workoutlog.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

import com.prudence.workoutlog.models.RegisterRequest
import com.prudence.workoutlog.models.RegisterResponse


interface ApiInterface {
    @POST("/register")
    fun registerUser(@Body registerRequest: RegisterRequest): Call<RegisterResponse>

    @POST("/login")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>
}
