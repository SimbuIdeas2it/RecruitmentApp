package com.chw.recruitmentapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chw.recruitmentapp.data.model.login.LoginRequest
import com.chw.recruitmentapp.data.model.login.LoginResponse
import com.chw.recruitmentapp.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
public class UserViewModel @Inject constructor(private val repository: AppRepository): ViewModel(){
    val returnedVal = MutableLiveData<LoginResponse>()
    fun login(request: LoginRequest){
        viewModelScope.launch(Dispatchers.IO) {
            val res = repository.login(request)
            returnedVal.postValue(res)
        }
    }
}