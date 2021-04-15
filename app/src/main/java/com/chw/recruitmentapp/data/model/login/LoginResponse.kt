package com.chw.recruitmentapp.data.model.login

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("status")
    var status: String? = null,

    @SerializedName("message")
    var message: String? = null,
)