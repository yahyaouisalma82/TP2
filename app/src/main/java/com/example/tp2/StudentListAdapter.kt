package com.example.tp2


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class StudentListAdapter(private val data: ArrayList<Student>) :
    RecyclerView.Adapter<StudentListAdapter.ViewHolder>(),Filterable {
    var dataFilterList = ArrayList<Student>()
    init {
        dataFilterList = data
    }

    class  ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val  textViewnom:TextView
        val  textViewprenom:TextView
        val imageView : ImageView


        init {
            textViewnom=itemView.findViewById((R.id.textViewnom))
            textViewprenom=itemView.findViewById((R.id.textViewnom))
            imageView=itemView.findViewById(R.id.imageViewFlag)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewnom.text=data[position].nom
        holder.textViewprenom.text=data[position].prenom
        if(data[position].genre == "man")
            holder.imageView.setImageResource(R.drawable.man)
        else
            holder.imageView.setImageResource(R.drawable.woman)

    }

    override fun getItemCount(): Int {
        return data.size
    }
    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    dataFilterList = data
                } else {
                    val resultList = ArrayList<Student>()
                    for (student in data) {
                        if (student.prenom.lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(student)
                        }
                    }
                    dataFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = dataFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                dataFilterList = results?.values as ArrayList<Student>
                notifyDataSetChanged()
            }

        }
    }



}
