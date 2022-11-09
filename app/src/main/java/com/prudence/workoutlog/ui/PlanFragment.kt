package com.prudence.workoutlog.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.prudence.workoutlog.R
import com.prudence.workoutlog.databinding.FragmentPlanBinding
import com.prudence.workoutlog.models.Exercise
import com.prudence.workoutlog.models.ExerciseCategory
import com.prudence.workoutlog.models.WorkoutPlan
import com.prudence.workoutlog.utils.Constants
import com.prudence.workoutlog.viewModel.ExerciseViewModel
import com.prudence.workoutlog.viewModel.WorkoutPlanViewModel
import java.util.*
import kotlin.collections.HashMap


class PlanFragment : Fragment() {
    val exerciseViewModel : ExerciseViewModel by viewModels()
    var binding: FragmentPlanBinding? = null
    val workoutPlanViewModel: WorkoutPlanViewModel by viewModels()
    lateinit var workoutPlanId : String

    val bind get() = binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlanBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onResume() {
        super.onResume()
        setDaySpinner()
        exerciseViewModel.fetchDbCategories()
        exerciseViewModel.fetchDbExercises()
        setupCategorySpinner()
        bind.btnadditem.setOnClickListener { clickAddItem() }
        checkForExistingWorkoutPlan()
        bind.btnsave.setOnClickListener { clickSaveDay() }
    }

    fun setDaySpinner (){
        val days = listOf("Select Day","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday")
        val days_adapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_item,days)
        days_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        bind.spdays.adapter = days_adapter
    }

    fun setupCategorySpinner(){
        exerciseViewModel.exerciseCategoryLiveData.observe(this, Observer { categories->
            val firstCategory = ExerciseCategory("0","Select Category")
            val displayCategories = mutableListOf(firstCategory)
            displayCategories.addAll(categories)
            val categoryAdapter = ExerciseCategoryAdapter(requireContext(), displayCategories)
            bind.spcategory.adapter = categoryAdapter
            bind.spcategory.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedCategory = displayCategories.get(position)
                    var categoryId = selectedCategory.categoryId
                    exerciseViewModel.getExerciseByCategoryId(categoryId)
                    setupExerciseSpinner()

                }
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
        })
    }

    fun setupExerciseSpinner(){
        exerciseViewModel.exerciseLiveData.observe(this, Observer { exercises->
            val firstCategory = Exercise("","1","","0","Select Exercise")
            val displayExercises = mutableListOf(firstCategory)
            displayExercises.addAll(exercises)
            val exerciseAdapter = ExerciseAdapter(requireContext(), displayExercises)
            bind.spexercise.adapter = exerciseAdapter
        })
    }
    fun clickAddItem () {
        var error = false
        if (bind.spdays.selectedItemPosition == 0){
            error = true
            Toast.makeText(requireContext(),"Select day",Toast.LENGTH_LONG).show()
        }
        if (bind.spcategory.selectedItemPosition == 0){
            error = true
            Toast.makeText(requireContext(),"Select category",Toast.LENGTH_LONG).show()
        }
        if (bind.spexercise.selectedItemPosition == 0){
            error = true
            Toast.makeText(requireContext(),"Select exercise",Toast.LENGTH_LONG).show()
        }
        if(!error){
            val selectedExercise = bind.spexercise.selectedItem as Exercise
            workoutPlanViewModel.selectedExerciseIds.add(selectedExercise.exerciseId)
            bind.spexercise.setSelection(0)
            bind.spcategory.setSelection(0)

        }

    }

    fun checkForExistingWorkoutPlan(){
        val prefs = requireContext().getSharedPreferences(Constants.SHARED_PREFS_FILE, Context.MODE_PRIVATE)
        val userId = prefs.getString(Constants.USER_ID,"").toString()
        workoutPlanViewModel.getExistingWorkoutPlans(userId)
        workoutPlanViewModel.workoutPlanLivedata.observe(this, Observer { workoutPlan ->
            if(workoutPlan == null){
                val newWorkoutPlan = WorkoutPlan(UUID.randomUUID().toString(),userId)
                workoutPlanViewModel.saveWorkoutPlan(newWorkoutPlan)
            }
            workoutPlanId = workoutPlan.workoutPlanId
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun getDayNumber(day:String):Int?{
        val dayMap = HashMap<String,Int>()
        dayMap.put("Monday", 1)
        dayMap.put("Tuesday", 2)
        dayMap.put("Wednesday", 3)
        dayMap.put("Thursday", 4)
        dayMap.put("Friday", 5)
        dayMap.put("Saturday",6)
        dayMap.put("Sunday",7)
        return  dayMap.get(day)?:-1
    }

    fun clickSaveDay(){
        if(bind.spdays.selectedItemPosition == 0){
            Toast.makeText(requireContext(),"Select Day ",Toast.LENGTH_LONG).show()

        }
        val day = bind.spdays.selectedItem.toString()
        val dayNumber = getDayNumber(day)
        if (workoutPlanViewModel.selectedExerciseIds.isEmpty()) {
            Toast.makeText(requireContext(),"Select Some Exercises for $day",Toast.LENGTH_LONG).show()
        }

        if (dayNumber != null) {
            workoutPlanViewModel.createWorkoutPlanItem(dayNumber,workoutPlanId)
        }
        bind.spdays.setSelection(0)

    }


}