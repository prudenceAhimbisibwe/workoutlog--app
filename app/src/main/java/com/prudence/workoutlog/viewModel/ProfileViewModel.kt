package com.prudence.workoutlog.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prudence.workoutlog.models.ProfileRequest
import com.prudence.workoutlog.models.ProfileResponse
import com.prudence.workoutlog.repository.ProfileRepository
import kotlinx.coroutines.launch

class ProfileViewModel:ViewModel() {
    val profileRepository = ProfileRepository()
    val  profileResponseLiveData = MutableLiveData<ProfileResponse>()
    val errorLiveData = MutableLiveData<String?>()

    fun createProfile (ProfileRequest: ProfileRequest){
        viewModelScope.launch {
            val response = profileRepository.create(ProfileRequest)
            if (response.isSuccessful) {
                profileResponseLiveData.postValue(response.body())
            }
            else{
                val error = response.errorBody()?.string()
                errorLiveData.postValue(error)
            }
        }
    }
}