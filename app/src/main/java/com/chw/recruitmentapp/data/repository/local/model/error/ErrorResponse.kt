package com.chw.recruitmentapp.data.repository.local.model.error

import com.google.gson.annotations.SerializedName
class ErrorResponse(

        @SerializedName("code")
        var code: Int = 0,

        @SerializedName("data")
        var data: String? = null,

        @SerializedName("message")
        var message: String? = null
)