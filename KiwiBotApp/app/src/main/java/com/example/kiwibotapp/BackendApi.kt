package com.example.kiwibotapp

import retrofit2.Call
import retrofit2.http.GET

interface BackendApi {
    @GET("/test-connection")
    fun testConnection(): Call<ConnectionResponse>
}

data class ConnectionResponse(val message: String)
