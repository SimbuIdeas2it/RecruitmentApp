package com.chw.recruitmentapp.data.repository.remote

import com.chw.recruitmentapp.data.api.ApiService
import com.chw.recruitmentapp.data.model.login.LoginRequest
import com.chw.recruitmentapp.data.model.login.LoginResponse
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val api: ApiService){

    suspend fun login(request: LoginRequest): LoginResponse {
        return  api.login(request)
    }
}