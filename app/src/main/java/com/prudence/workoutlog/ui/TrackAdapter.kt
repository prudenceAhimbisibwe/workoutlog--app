package com.prudence.workoutlog.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prudence.workoutlog.databinding.RowExerciseNameBinding
import com.prudence.workoutlog.models.Exercise

class TrackAdapter(val exerciseList: List<Exercise>, val logWorkout: LogWorkout) : RecyclerView.Adapter<ExerciseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val binding =
            RowExerciseNameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExerciseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val currentExercise = exerciseList.get(position)
        holder.binding.tvExerciseName.text = currentExercise.ExerciseName
        holder.binding.cbdone1.setOnClickListener{
            val weight = holder.binding.etWeightOne.text.toString()
            val reps = holder.binding.etReps.text.toString()
            logWorkout.onClickdone(set = 1, weight = weight.toInt(),reps = reps.toInt(),currentExercise.exerciseId)
        }
        holder.binding.cbdone.setOnClickListener{
            val weight = holder.binding.etWeight2.text.toString()
            val reps = holder.binding.editTextNumber4.text.toString()
            logWorkout.onClickdone(set = 2, weight = weight.toInt(),reps = reps.toInt(),currentExercise.exerciseId)
        }
        holder.binding.checkBox3.setOnClickListener{
            val weight = holder.binding.etWeight3.text.toString()
            val reps = holder.binding.etReps3.text.toString()
            logWorkout.onClickdone(set = 3, weight = weight.toInt(),reps = reps.toInt(),currentExercise.exerciseId)
        }
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }
}

class ExerciseViewHolder(val binding: RowExerciseNameBinding) :
    RecyclerView.ViewHolder(binding.root)

interface  LogWorkout {
    fun onClickdone (set:Int, weight:Int,reps:Int,exerciseId:String)
}
