package com.prudence.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer

import com.prudence.workoutlog.R
import com.prudence.workoutlog.databinding.ActivityHomeBinding
import com.prudence.workoutlog.utils.Constants
import com.prudence.workoutlog.viewModel.ExerciseViewModel

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var sharedPrefs: SharedPreferences
    lateinit var token : String
    val exerciseViewModel :  ExerciseViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPrefs = getSharedPreferences(Constants.SHARED_PREFS_FILE, MODE_PRIVATE)
        token = sharedPrefs.getString(Constants.ACCESS_TOKEN,"").toString()
        exerciseViewModel.fetchDbExercises()
        exerciseViewModel.fetchDbCategories()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNavigation()

    }

    override fun onResume() {
        super.onResume()
        exerciseViewModel.exerciseCategoryLiveData.observe(this, Observer { exerciseCategories->
            if(exerciseCategories.isEmpty()){
                exerciseViewModel.fetchExerciseCategories(token)
            }
        } )
        exerciseViewModel.exerciseLiveData.observe(this, Observer { exerciseCategories->
            if(exerciseCategories.isEmpty()){
                exerciseViewModel.fetchExercises(token)
            }
        } )
        exerciseViewModel.errorLiveData.observe(this, Observer { erroMsg->
            Toast.makeText(this,erroMsg,Toast.LENGTH_LONG).show()
        })
    }

    fun setupBottomNavigation(){
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.plan ->{
                    val transaction=supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fcvHome, PlanFragment())
                    transaction.commit()
                    true
                }
                R.id.Track ->{
                    val transaction=supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fcvHome, TrackFragment())
                    transaction.commit()
                    true
                }
                R.id.Profile ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fcvHome,
                        ProfileFragment()
                    ).commit()
                    true
                }
                else->false
            }
        }
    }
}
