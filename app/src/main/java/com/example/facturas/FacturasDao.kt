package com.example.facturas

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FacturasDao {
    @Query("SELECT * FROM facturas_table")
    suspend fun findAllFacturas(): List<FacturasEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(facturas: List<FacturasEntity>)
}