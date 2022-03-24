package com.example.facturas

import androidx.room.Database
import com.example.facturas.FacturasEntity
import androidx.room.RoomDatabase
import com.example.facturas.FacturasDao

@Database(version = 1, entities = [FacturasEntity::class])
public abstract class FacturasDatabase : RoomDatabase() {
    abstract fun facturasDao(): FacturasDao?
}