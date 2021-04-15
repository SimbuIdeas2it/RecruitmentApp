package com.chw.recruitmentapp.data.model.login

import com.google.gson.annotations.SerializedName

data class LoginRequest (
    @SerializedName("email")
    var email: String? = null,

    @SerializedName("password")
    var password: String? = null,

    @SerializedName("notification_id")
    var notification_id: String? = null
)