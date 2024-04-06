package com.example.sciflare

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @GET("create")
    fun getusersoveralllist(): retrofit2.Call<List<UserDataModel>>

    @POST("create")
    fun createUser(@Body userData: UserrequestData): retrofit2.Call<UserCreaateResponseModel>

}