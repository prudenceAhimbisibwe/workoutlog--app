package com.prudence.workoutlog.models

import com.google.gson.annotations.SerializedName

data class ProfileRequest(
    @SerializedName("user_id") var userId : String,
    @SerializedName("date_of_birth") var Date_Of_Birth : String,
    var sex : String,
    var weight : Double,
    var height : Int
)
