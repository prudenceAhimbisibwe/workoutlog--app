package com.prudence.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.prudence.workoutlog.databinding.ActivityLoginBinding
import com.prudence.workoutlog.models.LoginRequest
import com.prudence.workoutlog.models.LoginResponse
import com.prudence.workoutlog.utils.Constants
import com.prudence.workoutlog.viewModel.UserViewModel

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPrefs: SharedPreferences
    val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPrefs = getSharedPreferences(Constants.SHARED_PREFS_FILE, MODE_PRIVATE)

        binding.btnLogin.setOnClickListener { validationLogin()}
        binding.tvsignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onResume() {
        super.onResume()
        userViewModel.loginResponseLiveData.observe(this, Observer { loginResponse ->
            saveLoginDetails(loginResponse!!)
            Toast.makeText(baseContext, loginResponse?.message, Toast.LENGTH_LONG).show()
            startActivity(Intent(baseContext, HomeActivity::class.java))
            finish()
        })
        userViewModel.loginErrorLiveData.observe(this, Observer { registerError ->
            Toast.makeText(baseContext, registerError, Toast.LENGTH_LONG).show()
        })
    }
    fun validationLogin() {
        var error = false
        var email = binding.etEmail.text.toString()
        var password = binding.etWord.text.toString()
        if (email.isBlank()) {
            binding.tilEmail.error = "Email is required"
            error = true
        }
        if (password.isBlank()) {
            binding.tilWord.error = "Password is required"
            error = true
        }
        if (!error) {
            binding.pbLogin.visibility = View.VISIBLE
            val loginRequest = LoginRequest(email, password)
            userViewModel.loginUser(loginRequest)

        }
    }
        fun saveLoginDetails(loginResponse: LoginResponse) {
            val editor = sharedPrefs.edit()
            val token = "Bearer ${loginResponse.accessToken}"
            editor.putString(Constants.ACCESS_TOKEN, loginResponse.accessToken)
            editor.putString(Constants.USER_ID, loginResponse.userId)
            editor.putString(Constants.PROFILE_ID, loginResponse.profileId)
            editor.apply()
        }
    }
