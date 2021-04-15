package com.chw.recruitmentapp.data.repository

import com.chw.recruitmentapp.data.model.login.LoginRequest
import com.chw.recruitmentapp.data.model.login.LoginResponse
import com.chw.recruitmentapp.data.repository.remote.RemoteRepository
import javax.inject.Inject

class AppRepository @Inject constructor(private val remote: RemoteRepository){

    suspend fun login(request: LoginRequest): LoginResponse {
        return remote.login(request)
    }
}