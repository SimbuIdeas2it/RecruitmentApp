package com.chw.recruitmentapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chw.recruitmentapp.data.api.Resource
import com.chw.recruitmentapp.data.model.login.LoginRequest
import com.chw.recruitmentapp.data.model.login.LoginResponse
import com.chw.recruitmentapp.data.model.login.ProfileResponse
import com.chw.recruitmentapp.data.repository.AppRepository
import com.chw.recruitmentapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
public class UserViewModel @Inject constructor(private val repository: AppRepository): BaseViewModel(){
    private val _profileRes = MutableLiveData<Resource<ProfileResponse>>()
    val profileRes : LiveData<Resource<ProfileResponse>>
        get() = _profileRes

    private val _loginRes = MutableLiveData<Resource<LoginResponse>>()
    val loginRes : LiveData<Resource<LoginResponse>>
        get() = _loginRes

    fun login(request: LoginRequest){
        _loginRes.postValue(Resource.loading(null))
        viewModelScope.launch(Dispatchers.IO) {
            repository.login(request).let {
                if (it.isSuccessful) {
                    _loginRes.postValue(Resource.success(it.body()))
                    it.body()?.token?.let { it1 -> repository.saveLoginToken(it1) }
                }
                else {
                    _loginRes.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        }
    }

    fun profile(){
        _profileRes.postValue(Resource.loading(null))
        viewModelScope.launch(Dispatchers.IO) {
            repository.profile().let {
                if (it.isSuccessful) {
                    _profileRes.postValue(Resource.success(it.body()))
                }
                else {
                    _profileRes.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        }
    }

    fun doLogout() {
        repository.doLogout()
    }
}