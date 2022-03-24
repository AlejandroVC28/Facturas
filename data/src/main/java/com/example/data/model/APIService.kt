package com.example.data.model

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET("facturas")
    suspend fun getAllFacturas(): Response<FacturasResponse>
    //suspend fun getAllFacturasResponse(): Call<FacturasResponse> //PORQUE NO ES DE TIPO CALL Y SI DE TIPO RESPONSE O ALREVES
}