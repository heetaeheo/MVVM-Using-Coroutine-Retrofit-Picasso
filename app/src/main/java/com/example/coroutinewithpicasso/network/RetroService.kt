package com.example.coroutinewithpicasso.network

import com.example.coroutinewithpicasso.Model.RecyclerList
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

    @GET("repositories")
    suspend fun getDataFromApi(@Query("q")query : String): RecyclerList
}