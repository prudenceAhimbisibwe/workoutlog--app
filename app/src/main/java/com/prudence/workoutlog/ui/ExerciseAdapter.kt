package com.prudence.workoutlog.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.prudence.workoutlog.R
import com.prudence.workoutlog.models.Exercise

class ExerciseAdapter(context: Context, var exercises: List<Exercise>):
    ArrayAdapter<Exercise>(context,0,exercises) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position,convertView,parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position,convertView,parent)

    }

    fun getCustomView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = LayoutInflater.from(context).inflate(R.layout.custom_spinner_exercise_item,parent,false)
        val tvSpinnerText = view.findViewById<TextView>(R.id.tvExerciseSpinner)
        tvSpinnerText.text = exercises.get(position).ExerciseName
        return view
    }

}