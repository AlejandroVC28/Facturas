package com.example.facturas.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.data.model.FacturasRetrofit
import com.example.facturas.R

class CustomAdapter (private val dataSet: List<FacturasRetrofit>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.items, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the contents of the view with that element
        //Cuando esté pagada que no aparezca el cartel de estado
        if(dataSet[position].descEstado.equals("Pagada")){
            viewHolder.textViewEstado.visibility = View.GONE
        }
        viewHolder.textViewEstado.text = dataSet[position].descEstado
        viewHolder.textViewImporte.text = dataSet[position].importeOrdenacion.toString()
        viewHolder.textViewFecha.text = dataSet[position].fecha
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewEstado: TextView
        val textViewImporte: TextView
        val textViewFecha: TextView

        init {
            textViewEstado = view.findViewById(R.id.textViewEstado)
            textViewFecha = view.findViewById(R.id.textViewFecha)
            textViewImporte = view.findViewById(R.id.textViewImporte)
        }
    }
}
