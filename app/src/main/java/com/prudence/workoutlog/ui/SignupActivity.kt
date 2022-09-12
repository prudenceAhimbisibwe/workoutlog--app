package com.prudence.workoutlog.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.prudence.workoutlog.ApiClient
import com.prudence.workoutlog.api.ApiInterface
import com.prudence.workoutlog.databinding.ActivitySignupBinding
import com.prudence.workoutlog.models.RegisterRequest
import com.prudence.workoutlog.models.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.POST

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
        binding.btnsignup.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
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
        if(!error){
            val registerRequest= RegisterRequest(firstname,lastname,email,password,confirm)
            makeRegeister(registerRequest)
        }
    }
    fun makeRegeister(registerRequest: RegisterRequest){
        var  apiClient= ApiClient.buildApiClient(ApiInterface::class.java)
        val request= apiClient.registerUser(registerRequest)

        request.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>, response:
                Response<RegisterResponse>
            ) {
                if (response.isSuccessful){
                    Toast.makeText(baseContext,response.body()?.message, Toast.LENGTH_LONG).show()
                    startActivity(Intent(baseContext, LoginActivity::class.java))


                }
                else {
                    val error=response.errorBody()?.string()
                    Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast .makeText(baseContext,t.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    }






