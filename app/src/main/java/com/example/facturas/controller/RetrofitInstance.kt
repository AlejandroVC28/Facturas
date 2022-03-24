package com.example.facturas.controller

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{
        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://viewnextandroid.mocklab.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}