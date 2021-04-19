package com.chw.recruitmentapp.data.api

import com.chw.recruitmentapp.data.model.login.LoginRequest
import com.chw.recruitmentapp.data.model.login.LoginResponse
import com.chw.recruitmentapp.data.model.login.ProfileResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
//    @Headers("Accept: application/json")
    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

//    @Headers("Accept: application/json")
    @POST("profile")
    suspend fun profile(@Body user_id: String): Response<ProfileResponse>
}