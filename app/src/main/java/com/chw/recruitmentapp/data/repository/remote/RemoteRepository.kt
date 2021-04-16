package com.chw.recruitmentapp.data.repository.remote

import com.chw.recruitmentapp.data.api.ApiService
import com.chw.recruitmentapp.data.model.login.LoginRequest
import com.chw.recruitmentapp.data.model.login.LoginResponse
import com.chw.recruitmentapp.data.model.login.ProfileResponse
import retrofit2.Response
import javax.inject.Inject

class RemoteRepository @Inject constructor(private val api: ApiService){

    suspend fun login(request: LoginRequest): Response<LoginResponse> {
        return  api.login(request)
    }
    suspend fun profile(): Response<ProfileResponse> {
        return  api.profile("2")
    }
}