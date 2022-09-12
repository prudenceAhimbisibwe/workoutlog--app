package com.prudence.workoutlog.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.prudence.workoutlog.ApiClient
import com.prudence.workoutlog.api.ApiInterface
import com.prudence.workoutlog.databinding.ActivityLoginBinding
import com.prudence.workoutlog.models.LoginRequest
import com.prudence.workoutlog.models.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var sharedPrefs: SharedPreferences
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPrefs = getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)
        castViews()
        binding.btnLogin.setOnClickListener { validation() }
    }
    fun castViews(){
        binding.tvsignup
        binding.btnLogin
    }
    fun validation() {
        var error = false
        binding.tilEmail.error = null
        binding.tilEmail.error = null
        var email = binding.etEmail.text.toString()
        if (email.isBlank()) {
            binding.tilEmail.error = "Email is required"
            error = true

        }
        var password = binding.etWord.text.toString()
        if (password.isBlank()) {
            binding.tilWord.error = "Password is required"
            error = true
        }
        if (!error) {
            binding.pbLogin.visibility = View.VISIBLE
            val loginRequest = LoginRequest(email, password)
            makeLoginRequest(loginRequest)
        }
    }
    fun makeLoginRequest(loginRequest: LoginRequest) {
        var apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        var request = apiClient.loginUser(loginRequest)

        request.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
            call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                binding.pbLogin.visibility = View.GONE
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    Toast.makeText(baseContext, loginResponse?.message, Toast.LENGTH_LONG).show()
                    startActivity(Intent(baseContext, HomeActivity::class.java))


                } else {
                    val error = response.errorBody()?.string()
                    Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                binding.pbLogin.visibility = View.GONE
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }

        })
        fun saveLoginDetails(loginResponse: LoginResponse){
            val editor = sharedPrefs.edit()
            editor.putString("ACCESS_TOKEN",loginResponse.accessToken)
            editor.putString("USER_ID",loginResponse.userId)
            editor.putString("PROFILE_ID",loginResponse.profileId)
            editor.apply()

        }
    }
}


