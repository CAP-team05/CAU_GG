package com.example.caugg.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

data class RegisterRequest(val email: String, val password: String, val nickname: String)
data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val message: String, val nickname: String)

interface ApiService {
    @POST("/register")
    fun register(@Body request: RegisterRequest): Call<Unit>

    @POST("/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}


object RetrofitInstance {
    private const val BASE_URL = "http://10.0.2.2:8080/"

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}