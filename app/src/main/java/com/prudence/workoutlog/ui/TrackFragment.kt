package com.prudence.workoutlog.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.prudence.workoutlog.R
import com.prudence.workoutlog.databinding.FragmentTrackBinding
import com.prudence.workoutlog.models.WorkoutLogRecord
import com.prudence.workoutlog.utils.Constants
import com.prudence.workoutlog.viewModel.ExerciseViewModel
import com.prudence.workoutlog.viewModel.WorkoutLogRecordViewModel
import com.prudence.workoutlog.viewModel.WorkoutPlanViewModel
import java.time.LocalDate
import java.util.*


class TrackFragment : Fragment(), LogWorkout {
    lateinit var binding: FragmentTrackBinding
    val workoutPlanViewModel: WorkoutPlanViewModel by viewModels()
    val exerciseViewModel: ExerciseViewModel by viewModels()
    val workoutLogRecordViewModel: WorkoutLogRecordViewModel by viewModels()
    lateinit var prefs: SharedPreferences
    lateinit var userId: String
    lateinit var workoutPlanItemId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrackBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("NewApi")
    override fun onResume() {
        super.onResume()
        prefs = requireContext().getSharedPreferences(Constants.SHARED_PREFS_FILE, Context.MODE_PRIVATE)
        userId = prefs.getString(Constants.USER_ID, Constants.EMPTY_STRING).toString()
        workoutPlanViewModel.getExistingWorkoutPlans(userId)

        workoutPlanViewModel.workoutPlanLivedata.observe(this, Observer { workoutplan ->
            val workoutPlanId = workoutplan.workoutPlanId
            val dayNumber = LocalDate.now().dayOfWeek.value
            workoutPlanViewModel.getTodayWorkoutPlanItem(workoutPlanId, dayNumber)
                .observe(this, Observer { workoutplanitem ->
                    if (workoutplanitem != null) {
                        workoutPlanItemId = workoutplanitem.workoutPlanItemId
                        val todayExerciseIds = workoutplanitem.exerciseId
                        exerciseViewModel.getExercisesIds(todayExerciseIds)
                            .observe(this, Observer { exercise ->
                                val adapter = TrackAdapter(exercise, this)
                                binding.RvTrack.layoutManager =
                                    LinearLayoutManager(requireContext())
                                binding.RvTrack.adapter = adapter
                            })

                    } else {
                        Toast.makeText(
                            requireContext(),
                            "No workout plan item found for today. create one to continue",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                })
        })



    }

    override fun onClickdone(set: Int, weight: Int, reps: Int, exerciseId: String) {
        val workoutLogRecord = WorkoutLogRecord(
            workoutLogId = UUID.randomUUID().toString(),
            date = "",
            exerciseId = exerciseId,
            set = set,
            reps = reps,
            weight = weight,
            workoutPlanItemId = workoutPlanItemId,
            userId = userId
        )
        workoutLogRecordViewModel.saveWorkoutLogRecord(workoutLogRecord)
    }
}
