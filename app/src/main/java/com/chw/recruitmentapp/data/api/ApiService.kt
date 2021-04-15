package com.chw.recruitmentapp.data.api

import com.chw.recruitmentapp.data.model.login.LoginRequest
import com.chw.recruitmentapp.data.model.login.LoginResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Accept: application/json")
    @POST("login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

}