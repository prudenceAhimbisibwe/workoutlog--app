package com.prudence.workoutlog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignupActivity : AppCompatActivity() {
    lateinit var btnsignup: Button
    lateinit var tilfirstname: TextInputLayout
    lateinit var etfirstname: TextInputEditText
    lateinit var tillastname: TextInputLayout
    lateinit var etlastname: TextInputEditText
    lateinit var tilemail: TextInputLayout
    lateinit var etemail: TextInputEditText
    lateinit var tvlogin: TextView
    lateinit var tilPassword: TextInputLayout
    lateinit var etpassword: TextInputEditText
    lateinit var tilconfirm: TextInputLayout
    lateinit var etconfirm: TextInputEditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        tilfirstname = findViewById(R.id.tilfirstname)
        etfirstname = findViewById(R.id.etfirstname)
        tillastname = findViewById(R.id.tillastname)
        etfirstname = findViewById(R.id.etlastname)
        tilemail = findViewById(R.id.tilpassword)
        etemail = findViewById(R.id.etpassword)
        tilPassword = findViewById(R.id.tilPassword)
        etpassword = findViewById(R.id.etPassword)
        tilconfirm = findViewById(R.id.tilconfirm)
        tvlogin = findViewById(R.id.tvlogin)
        etconfirm = findViewById(R.id.etconfirm)
        btnsignup=findViewById(R.id.btnsignup)
        btnsignup.setOnClickListener { validation() }

        tvlogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
    fun validation() {
        var error = false
        tilfirstname.error = null
        tillastname.error = null
        tilemail.error = null
        tilPassword.error = null
        tilconfirm.error = null
        var firstname = etfirstname.text.toString()
        if (firstname.isBlank()) {
            tilfirstname.error = "First name is required"
            error = true

        }
        var lastname = etlastname.text.toString()
        if (lastname.isBlank()) {
            tillastname.error = "Last name is required"
            error = true
        }
        var email = etemail.text.toString()
        if (email.isBlank()) {
            tilemail.error = "Email is required"
            error = true
        }
        var password = etpassword.text.toString()
        if (password.isBlank()) {
            tilPassword.error = "Password is required"
            error = true
        }
        var confirm = etconfirm.text.toString()
        if (confirm.isBlank()) {
            tilconfirm.error = "Ivalid password"
            error = true
        }

        if (error!=true) {

        }
    }
}


