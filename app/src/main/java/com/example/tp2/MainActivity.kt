package com.example.tp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val spinner : Spinner by lazy { findViewById(R.id.spinner) }
    lateinit var recyclerView: RecyclerView
    lateinit var researchbar : EditText
    val s1 =Student("yahyaoui","salma","woman")
    val s2 =Student("Harbi","mourad","man")
    val s3 =Student("Harbi","Dhouka","woman")
    var StudentList = StudentListAdapter(arrayListOf<Student>(s1,s2,s3))
    var StudentListTP = StudentListAdapter(arrayListOf<Student>(s1,s3))
    var StudentListCOURS = StudentListAdapter(arrayListOf<Student>(s1,s2))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var matieres = listOf<String>("Cours", "TP")
        recyclerView = findViewById(R.id.recycler)
        researchbar = findViewById(R.id.textViewsearch)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        spinner.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, matieres)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val matiere = matieres.get(position)
                if (matiere == "TP") {
                    recyclerView.adapter = StudentListTP
                }
                else
                    recyclerView.adapter =StudentListCOURS


                Toast.makeText(this@MainActivity, "matiere : $matiere", Toast.LENGTH_LONG).show()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                recyclerView.adapter = StudentList
            }
        }
        val textWatcher = object : TextWatcher {
            lateinit var filtered : StudentListAdapter
            override fun afterTextChanged(s: Editable?) {

            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filtered = if (recyclerView.adapter==StudentListTP) {
                    StudentListTP
                } else
                    StudentListCOURS

                filtered.filter.filter(s)
                recyclerView.adapter = StudentListAdapter(filtered.dataFilterList)
            }
        }
        researchbar.addTextChangedListener(textWatcher)
    }
}