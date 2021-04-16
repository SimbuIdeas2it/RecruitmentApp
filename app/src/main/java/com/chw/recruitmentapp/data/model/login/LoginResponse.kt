package com.chw.recruitmentapp.data.model.login

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("status")
    var status: String? = null,

    @SerializedName("message")
    var message: String? = null,

    @SerializedName("token")
    var token: String? = null,

)

data class ProfileResponse (
    @SerializedName("status")
    var status: String? = null,

    @SerializedName("message")
    var message: String? = null,

    @SerializedName("datas")
    var datas: ProfileDetailResponse? = null,
)

data class ProfileDetailResponse (
    @SerializedName("id")
    var id: String? = null,

    @SerializedName("firstname")
    var firstname: String? = null,

    @SerializedName("lastname")
    var lastname: String? = null,

    @SerializedName("email")
    var email: String? = null,

    @SerializedName("mobile")
    var mobile: String? = null,
)