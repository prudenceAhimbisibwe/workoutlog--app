package com.prudence.workoutlog.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prudence.workoutlog.models.LoginRequest
import com.prudence.workoutlog.models.LoginResponse
import com.prudence.workoutlog.models.RegisterRequest
import com.prudence.workoutlog.models.RegisterResponse
import com.prudence.workoutlog.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {
    val userRepository = UserRepository()
    val loginResponseLiveData = MutableLiveData<LoginResponse>()
    val loginErrorLiveData = MutableLiveData<String?>()

    val registerResponseLiveData = MutableLiveData<RegisterResponse>()
    val  registerErrorLiveData = MutableLiveData<String?>()

    fun loginUser(loginRequest:LoginRequest){
        viewModelScope.launch {
            val response = userRepository.loginUser(loginRequest)
            if (response.isSuccessful){
                loginResponseLiveData.postValue(response.body())
            }
            else{
                val error= response.errorBody()?.string()
                loginErrorLiveData.postValue(error)
            }
        }
    }
    fun RegisterUser(RegisterRequest:RegisterRequest){
        viewModelScope.launch {
            val response = userRepository.registerUser(RegisterRequest)
            if (response.isSuccessful){
                registerResponseLiveData.postValue(response.body())
            }
            else{
                val error= response.errorBody()?.string()
                registerErrorLiveData.postValue(error)
            }
        }
    }
}
