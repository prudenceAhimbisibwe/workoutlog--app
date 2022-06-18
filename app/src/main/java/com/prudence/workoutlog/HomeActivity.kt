package com.prudence.workoutlog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.provider.ContactsContract
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.prudence.workoutlog.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding:ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castView()
        setupBottomNav()

    }
    fun castView(){

    }
    fun setupBottomNav(){
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.plan ->{
                    val transaction=supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fcvHome,PlanFragment())
                    transaction.commit()
                    true
                }
                R.id.Track->{
                    val transaction=supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fcvHome,TrackFragment())
                    transaction.commit()
                    true
                }
                R.id.Profile->{
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome,ProfileFragment()).commit()
                    true
                }
                else->false
            }
        }
    }
}