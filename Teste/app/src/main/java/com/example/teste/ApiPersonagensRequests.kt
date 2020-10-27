package com.example.teste

import retrofit2.Call
import retrofit2.http.*


interface ApiPersonagensRequests {

    @GET("/personagem")
    fun getFilmes(): Call<List<Personagem>>

    @GET("/personagem/{id}")
    fun getFilme(@Path("id") id:Integer): Call<Personagem>

    @DELETE("/personagem/{id}")
    fun deleteFilme(@Path("id") id:Integer): Call<Void>
    // Call<Void> Indica que este Endpoint não vai retornar nada

    @POST("/personagem")
    fun postFilme(@Body novoPersonagem:Personagem): Call<Void>

}