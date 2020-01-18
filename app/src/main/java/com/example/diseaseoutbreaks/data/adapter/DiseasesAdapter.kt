package com.example.diseaseoutbreaks.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diseaseoutbreaks.data.Model.Item
import com.example.diseaseoutbreaks.R
import com.example.diseaseoutbreaks.data.Model.DataClass
import kotlinx.android.synthetic.main.list_item.view.*

class DiseasesAdapter(val items : DataClass) : RecyclerView.Adapter<DiseasesAdapter.DiseaseViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DiseaseViewHolder(
           LayoutInflater.from(parent.context).inflate(
               R.layout.list_item,
               parent,
               false
           )
       )



    override fun getItemCount() = items.items.size

    override fun onBindViewHolder(holder: DiseaseViewHolder, position: Int) {

        val disease = items.items[position]

        holder.view.title.text = disease.title
        holder.view.publication_date.text = "Publication Date : ${disease.pubDate}"
        holder.view.link.text = "Link : ${disease.link}"
        holder.view.description.text = disease.description
    }

    class DiseaseViewHolder (val view : View) : RecyclerView.ViewHolder(view)
}