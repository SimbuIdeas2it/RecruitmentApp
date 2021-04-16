package com.chw.recruitmentapp.data.repository

import com.chw.recruitmentapp.data.model.login.LoginRequest
import com.chw.recruitmentapp.data.model.login.LoginResponse
import com.chw.recruitmentapp.data.model.login.ProfileResponse
import com.chw.recruitmentapp.data.repository.local.LocalRepository
import com.chw.recruitmentapp.data.repository.remote.RemoteRepository
import retrofit2.Response
import javax.inject.Inject

class AppRepository @Inject constructor(private val remote: RemoteRepository, private val localRepository: LocalRepository){

    suspend fun login(request: LoginRequest): Response<LoginResponse> {
        return remote.login(request)
    }

    suspend fun profile(): Response<ProfileResponse> {
        return remote.profile()
    }

    fun saveLoginToken(token: String) {
        localRepository.saveAccessToken(token)
    }

    fun doLogout() {
        localRepository.clearUserData()
    }
}