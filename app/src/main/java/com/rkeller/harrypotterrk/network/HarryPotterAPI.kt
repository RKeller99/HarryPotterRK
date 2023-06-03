package com.rkeller.harrypotterrk.network

import com.rkeller.harrypotterrk.model.DetallesPersonaje
import com.rkeller.harrypotterrk.model.Personaje
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface HarryPotterAPI {

    @GET
    fun getPersonajes(
        @Url url:String? // getPersonajes("api/character/")
    ): Call<ArrayList<Personaje>>

    @GET("api/character/{id}")          // getPersonajesDetails("9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8")
    fun getPersonajesDetails(           // api/character/9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8
        @Path("id") id:String?
    ): Call<DetallesPersonaje>



}