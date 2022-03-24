package com.example.facturas.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.data.model.APIService
import com.example.data.model.FacturasRetrofit
import com.example.data.model.FacturasResponse
import com.example.facturas.FacturasDatabase
import com.example.facturas.R
import com.example.facturas.controller.CustomAdapter
import com.example.facturas.controller.RetrofitInstance.Companion.getRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var adapter: CustomAdapter
    private val facturasML = mutableListOf<FacturasRetrofit>()
    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setToolbar()
        var textoprueba = findViewById<TextView>(R.id.prueba_room)

        //RecyclerView
        recycler = findViewById<RecyclerView>(R.id.recyclerId)
        adapter = CustomAdapter(facturasML)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        //Retrofit
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<FacturasResponse> =
                getRetrofit().create(APIService::class.java).getAllFacturas()//.execute()
            val callBody = call.body()
            runOnUiThread {
                //Si la llamada es exitosa se almacenan las facturas recibidas de la API en una variable local
                if (call.isSuccessful) {
                    //si callBody es nulo facturas = lista vacía
                    val callFacturas = callBody?.facturas ?: emptyList<FacturasRetrofit>()
                    facturasML.clear()
                    facturasML.addAll(callFacturas)
                    adapter.notifyDataSetChanged()
                } else {
                    showError()
                }
            }
        }

        //Room
        //Construye la base de datos
        var db: FacturasDatabase =
            Room.databaseBuilder(applicationContext, FacturasDatabase::class.java, "facturas_table")
                .build()
        //Hago la llamada y guardo las facturas en la base de datos
        CoroutineScope(Dispatchers.IO).launch {
            var Rfacturas = db.facturasDao()?.findAllFacturas()
            if(Rfacturas != null) {
                db.facturasDao()!!.insertAll(Rfacturas)
            }
        }
    }

    //Quitar las facturas pagadas
    fun removeFacturasPagadas(facturas: MutableList<FacturasRetrofit>) {
        var num = 0
        var num1 = 0
        var estado: String
        val indicesPagadas: MutableList<Int> = arrayListOf()
        while(facturas.isNotEmpty()) {
            estado = facturas[num].descEstado
            if (estado == "Pagada"){
                indicesPagadas.add(num)
            }
            num++
        }
        while(indicesPagadas.isNotEmpty()) {
            facturas.removeAt(indicesPagadas[num1])
            num1++
        }
    }

    //Quitar las facturas pendientes
    fun removeFacturasPendientes(facturas: MutableList<FacturasRetrofit>) {
        var num = 0
        var num1 = 0
        var estado: String
        val indicesPendientes: MutableList<Int> = arrayListOf()
        while(facturas.isNotEmpty()) {
            estado = facturas[num].descEstado
            if (estado == "Pendiente de pago"){
                indicesPendientes.add(num)
            }
            num++
        }
        while(indicesPendientes.isNotEmpty()) {
            facturas.removeAt(indicesPendientes[num1])
            num1++
        }
    }

    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    // Añadir la Toolbar
    private fun setToolbar() {
        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
    }

    //Menú
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.items_mainactivity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.filtrosIB -> {
                val intent = Intent(this, FiltrosActivity::class.java)
                //intent.putExtra()
                startActivity(intent)
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}
