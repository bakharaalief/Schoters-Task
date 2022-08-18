package com.bakharaalief.schoterstask.data.remote.retrofit

import com.bakharaalief.schoterstask.data.remote.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/top-headlines")
    suspend fun getNews(@Query("apiKey") apiKey: String, @Query("country") country: String): NewsResponse
}