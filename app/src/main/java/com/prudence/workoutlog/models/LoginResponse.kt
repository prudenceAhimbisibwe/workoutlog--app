package com.prudence.workoutlog.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
     var message: String,
     @SerializedName("access_token")var accessToken:String,
     @SerializedName("user_id")var userId:String,
     @SerializedName("profile_id") var profileId:String
)

