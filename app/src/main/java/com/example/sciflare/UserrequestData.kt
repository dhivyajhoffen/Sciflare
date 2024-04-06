package com.example.sciflare

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserrequestData(
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("email")
    val email: String,
    @Expose
    @SerializedName("mobile")
    val mobile: String,
    @Expose
    @SerializedName("gender")
    val gender: String,
)