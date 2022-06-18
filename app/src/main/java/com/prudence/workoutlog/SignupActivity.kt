package com.prudence.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.prudence.workoutlog.databinding.ActivityLoginBinding
import com.prudence.workoutlog.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    lateinit var binding:ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvlogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        validationSignup()
    }


    fun validationSignup() {
        var error = false
        binding.tilfirstname.error = null
        binding.tillastname.error = null
        binding.tilPword.error = null
        binding. tilconfirm.error = null

        var firstname = binding.etfirstname.text.toString()
        if (firstname.isBlank()) {
            binding.tilfirstname.error = "First name is required"
            error = true
        }
        var lastname = binding.etlastname.text.toString()
        if (lastname.isBlank()) {
            binding.tillastname.error = "Last name is required"
            error = true
        }
        var email = binding.etWord.text.toString()
        if (email.isBlank()) {
            binding.tilPword.error = "Email is required"
            error = true
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.tilWord.error = "Not valid email address"
            error = true

        }
        var password = binding.etPword.text.toString()
        if (password.isBlank()) {
            binding.tilPword.error = "Password is required"
            error = true

        }
        var confirm = binding.etconfirm.text.toString()
        if (confirm.isBlank()) {
            binding.tilconfirm.error = "Invalid password,enter password"
            error = true
        }
        if (password!=confirm){
            binding.tilconfirm.error="Enter password"
        }
        if(error!=false){
        }
    }
    }






