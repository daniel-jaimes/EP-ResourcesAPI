package com.strapp.ep_recursosapi.data.remote

import com.strapp.ep_recursosapi.data.model.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface JSONPlaceHolderAPIService {
    @GET("pokemon/{id_name}")
    fun getPok(@Path("id_name") id_name: String): Call<Pokemon>

}