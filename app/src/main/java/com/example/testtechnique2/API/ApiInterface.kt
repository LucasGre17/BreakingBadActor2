package com.example.testtechnique2.API

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("api/character/random")
    fun getData(): Call<List<ActorItem>>
}