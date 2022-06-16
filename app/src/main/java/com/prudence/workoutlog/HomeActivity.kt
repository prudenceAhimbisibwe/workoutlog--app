package com.prudence.workoutlog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    lateinit var bnvHome: BottomNavigationView
    lateinit var fcvHome:FragmentContainerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        castView()
        setupBottomNav()

    }
    fun castView(){
        bnvHome=findViewById(R.id.bottom_navigation)
        fcvHome=findViewById(R.id.fcvHome)
    }
    fun setupBottomNav(){
        bnvHome.setOnItemSelectedListener{ item->
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
                    val transaction=supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.fcvHome,ProfileFragment())
                    transaction.commit()
                    true
                }
                else->false
            }
        }
    }
}