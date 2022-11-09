package com.prudence.workoutlog.models

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("date_of_birth")  var Date_Of_Birth : String,
    var Sex : String,
    @SerializedName("user_id") var userId : String,
    @SerializedName("profile_id") var profileId : String,
    var weight : Double,
    var height : Int
)
