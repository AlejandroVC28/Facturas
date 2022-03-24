package com.example.facturas

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "facturas_table")
data class FacturasEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "descEstado") val descEstado: String,
    @ColumnInfo(name = "importeOrdenacion") val importeOrdenacion: Double,
    @ColumnInfo(name = "fecha") val fecha: String
)