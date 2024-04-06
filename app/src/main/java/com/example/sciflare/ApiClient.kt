package com.example.sciflare

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

//    var BASE_URL:String="https://crudcrud.com/api/8599d4f19c7240ffa09a656c8f55e13e/"
//    var BASE_URL:String="https://crudcrud.com/api/2a284bcff3de46ae817e135cbae23782/"
//    var BASE_URL:String="https://crudcrud.com/api/2db73701eb5e452bb06376385021f694/"
    var BASE_URL:String="https://crudcrud.com/api/ae59f3e0fdf140deb4cd3fff9e4d7c82/"
    val getClient: ApiInterface
        get() {

            val gson = GsonBuilder()
                .setLenient()
                .create()
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            return retrofit.create(ApiInterface::class.java)

        }
}