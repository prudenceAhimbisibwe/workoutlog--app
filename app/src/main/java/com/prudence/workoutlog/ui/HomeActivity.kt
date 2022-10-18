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
    lateinit var sharedPrefs:SharedPreferences
    lateinit var binding:ActivityHomeBinding
    val exerciseViewModel:ExerciseViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNavigation()

        binding.tvLogout.setOnClickListener {
            sharedPrefs = getSharedPreferences(Constants.SHARED_PREFS_FILE, MODE_PRIVATE)
            val token = sharedPrefs.getString(Constants.ACCESS_TOKEN,Constants.EMPTY_STRING)
            exerciseViewModel.fetchExerciseCategories(token!!)
            val editor = sharedPrefs.edit()
            editor.putString(Constants.ACCESS_TOKEN,"")
            editor.putString(Constants.USER_ID,"")
            editor.putString(Constants.PROFILE_ID,"")
            editor.apply()
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
    override fun onResume() {
        super.onResume()
        exerciseViewModel.exerciseCategoryLiveData.observe(this, Observer { exerciseCategories ->
            Toast.makeText(baseContext,"fetched ${exerciseCategories.size} categories", Toast.LENGTH_LONG).show()
        })
        exerciseViewModel.errorLiveData.observe(this, Observer { errorMsg ->
            Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
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
