package com.prudence.workoutlog.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.prudence.workoutlog.api.ApiClient
import com.prudence.workoutlog.api.ApiInterface
import com.prudence.workoutlog.databinding.ActivitySignupBinding
import com.prudence.workoutlog.models.RegisterRequest
import com.prudence.workoutlog.viewModel.UserViewModel

class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvlogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnsignup.setOnClickListener { validationSignup() }
    }
        override fun onResume() {
            super.onResume()
            userViewModel.registerResponseLiveData.observe(this, Observer { registerResponse ->
                Toast.makeText(baseContext, registerResponse.message, Toast.LENGTH_LONG).show()
                startActivity(Intent(baseContext, LoginActivity::class.java))
            })
            userViewModel.registerErrorLiveData.observe(this, Observer { error ->
                Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
            })
        }
    fun validationSignup() {
        var error = false
        var firstname = binding.etfirstname.text.toString()
        var lastname = binding.etlastname.text.toString()
        var email = binding.etWord.text.toString()
        var password = binding.etPword.text.toString()
        var confirm = binding.etconfirm.text.toString()

        if (firstname.isBlank()) {
            binding.tilfirstname.error = "First name is required"
            error = true
        }
        if (lastname.isBlank()) {
            binding.tillastname.error = "Last name is required"
            error = true
        }
        if (email.isBlank()) {
            binding.tilPword.error = "Email is required"
            error = true
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilWord.error = "Not valid email address"
            error = true

        }
        if (password.isBlank()) {
            binding.tilPword.error = "Password is required"
            error = true

        }
        if (confirm.isBlank()) {
            binding.tilconfirm.error = "Invalid password,enter password"
            error = true
        }
        if (password != confirm) {
            binding.tilconfirm.error = "Enter password"
        }
        if (!error) {
            val registerRequest = RegisterRequest(firstname, lastname, email, password, confirm)
            userViewModel.RegisterUser(registerRequest)
        }
    }
}
