package com.example.facturas.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import com.example.data.model.FacturasRetrofit
import com.example.facturas.R
import com.google.android.material.slider.RangeSlider
import com.google.android.material.slider.Slider

class FiltrosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.filtros)
        setToolbar()

        val fechaDesde = findViewById<EditText>(R.id.editTextDate1)
        val fechaHasta = findViewById<EditText>(R.id.editTextDate2)
        val slider = findViewById<Slider>(R.id.sliderImporte)


        /* SLIDER
        val slider: RangeSlider = findViewById(R.id.sliderImporte)
        slider.addOnSliderTouchListener(object : RangeSlider.OnSliderTouchListener {
            override fun onStartTrackingTouch(slider: Slider) {
                val values = slider.values
                //Those are the satrt and end values of sldier when user start dragging
                Log.i("SliderPreviousValueFrom", values[0].toString())
                Log.i("SliderPreviousValueTo", values[1].toString())
            }

            override fun onStopTrackingTouch(slider: Slider) {
                val values = slider.values
                //Those are the new updated values of sldier when user has finshed dragging
                Log.i("SliderNewValueFrom", values[0].toString())
                Log.i("SliderNewValueTo", values[1].toString())

                textView.setText("Start value: ${values[0]}, End value: ${values[1]}")
            }
        })

        slider.addOnChangeListener { slider, value, fromUser ->
            // Responds to when slider's value is changed
        }*/
    }
    fun onCheckboxClicked(view: View){
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked
            when (view.id) {
                R.id.checkBoxPagadas -> {
                    if (checked) {
                        //No hacer nada
                    } else {
                        //Y si no está checkeado sacarlo de la lista
                        //removeFacturasPagadas(facturasML)
                        //adapter.notifyDataSetChanged()
                    }
                }
                R.id.checkBoxAnuladas -> {
                    if (checked) {
                        //No hacer nada
                    } else {
                        //Y si no está checkeado sacarlo de la lista
                    }
                }
                R.id.checkBoxCuotafija -> {
                    if (checked) {
                        //No hacer nada
                    } else {
                        //Y si no está checkeado sacarlo de la lista
                    }
                }
                R.id.checkBoxPendientesdepago -> {
                    if (checked) {
                        //No hacer nada
                    } else {
                        //Y si no está checkeado sacarlo de la lista
                        //removeFacturasPendientes(facturasML)
                        //adapter.notifyDataSetChanged()
                    }
                }
                R.id.checkBoxPlandepago -> {
                    if (checked) {
                        //No hacer nada
                    } else {
                        //Y si no está checkeado sacarlo de la lista
                    }
                }
            }
        }
    }

    private fun setToolbar() {
        // Añadir la Toolbar
        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.items_filtros, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.closeIB -> {
                finish()
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}