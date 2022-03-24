package com.example.data.model

import com.google.gson.annotations.SerializedName

data class FacturasRetrofit(
    @SerializedName("descEstado") var descEstado: String,
    @SerializedName("importeOrdenacion") var importeOrdenacion: Double,
    @SerializedName("fecha") var fecha: String
)