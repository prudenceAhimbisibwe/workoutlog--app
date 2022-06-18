package com.prudence.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.prudence.workoutlog.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvsignup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        validation()
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
        if (error!=true) {

        }
    }
}


