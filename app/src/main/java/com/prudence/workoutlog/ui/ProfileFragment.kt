package com.prudence.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.prudence.workoutlog.R
import com.prudence.workoutlog.databinding.FragmentProfileBinding
import com.prudence.workoutlog.models.ProfileResponse

class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding
    lateinit var sharedPrefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        sharedPrefs = activity?.applicationContext!!.getSharedPreferences("WORKOUTLOG_PREFS", AppCompatActivity.MODE_PRIVATE)
        binding.tvLogout.setOnClickListener {
            LogoutRequest()
        }
        return  binding.root
    }

//    override fun onResume() {
//        super.onResume()
//        profileViewModel.profileResponseLiveData.observe(this, Observer { profileresponse ->
//            Toast.makeText(context, ProfileResponse .user, Toast.LENGTH_LONG).show()
//        })
//        profileViewModel.errorLiveData.observe(this, Observer { error->
//            Toast.makeText(context,error, Toast.LENGTH_LONG).show()
//        })
//    }

    fun LogoutRequest () {
        sharedPrefs.edit().clear().apply()
        val intent = Intent(context, LoginActivity::class.java)
        startActivity(intent)
    }
}
