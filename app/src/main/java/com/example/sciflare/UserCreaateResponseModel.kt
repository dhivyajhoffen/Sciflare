package com.example.sciflare

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserCreaateResponseModel(
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
    @Expose
    @SerializedName("_id")
    val _id: String
)