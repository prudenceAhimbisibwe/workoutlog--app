package com.prudence.workoutlog

import android.content.Context
import android.app.Application

class WorkoutLog:Application() {
    companion object{
        lateinit var appContext: Context
    }
    override fun onCreate(){
        super.onCreate()
        appContext = applicationContext
    }

}