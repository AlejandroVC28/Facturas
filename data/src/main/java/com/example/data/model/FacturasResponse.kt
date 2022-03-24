package com.example.data.model

import com.google.gson.annotations.SerializedName;

data class FacturasResponse(
    @SerializedName("numFacturas") var numFacturas: Int,
    @SerializedName("facturas") var facturas: List<FacturasRetrofit>,
)