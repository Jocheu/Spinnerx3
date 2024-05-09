package com.example.spinnermiku

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val buton = findViewById<Button>(R.id.button)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val liczba1 = findViewById<EditText>(R.id.editTextNumberSigned)
        val liczba2 = findViewById<EditText>(R.id.editTextNumberSigned2)
        val wynik = findViewById<TextView>(R.id.textView3)
        findViewById<View>(R.id.rest).isVisible = false
        ArrayAdapter.createFromResource(this,R.array.actions,android.R.layout.simple_spinner_item).also {
            adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.setSelection(0,false)
        spinner.onItemSelectedListener = this
        buton.setOnClickListener {
            if(liczba1.text.toString()!=""&&liczba2.text.toString()!=""){
                when(spinner.selectedItemPosition){
                    0 -> {
                        val alertcos = AlertDialog.Builder(this)
                        alertcos.setMessage("Wybierz akcję")
                        alertcos.setPositiveButton(":(",null)
                        alertcos.show()
                    }
                    1->{
                        wynik.text = (liczba1.text.toString().toInt()+liczba2.text.toString().toInt()).toString()
                    }
                    2->{
                        wynik.text = (liczba1.text.toString().toInt()-liczba2.text.toString().toInt()).toString()
                    }
                    3->{
                        wynik.text = (liczba1.text.toString().toInt()*liczba2.text.toString().toInt()).toString()
                    }
                    4->{
                        if(liczba2.text.toString().toInt()!=0) {
                            wynik.text = (liczba1.text.toString().toInt() / liczba2.text.toString()
                                .toInt()).toString()
                        }else{
                            val alertcos = AlertDialog.Builder(this)
                            alertcos.setMessage("Mily czlowieku nie dziel przez 0")
                            alertcos.setPositiveButton(":(",null)
                            alertcos.show()
                        }
                    }
                }
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val spiner = findViewById<Spinner>(R.id.spinner)
        if(spiner.selectedItemPosition != 0){
            findViewById<View>(R.id.rest).isVisible = true
            findViewById<TextView>(R.id.textView).text = spiner.selectedItem.toString()
        }

    }
}

