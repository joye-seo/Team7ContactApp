package com.example.team7contactapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NameAdapter(val  NameList : ArrayList<Names>) :  RecyclerView.Adapter<NameAdapter.CustomViewHolder>()
{


    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): NameAdapter.CustomViewHolder {

        val  view = LayoutInflater.from(parent.context).inflate(R.layout.adrees_item,parent,false)
        return CustomViewHolder(view)

    }

    override fun getItemCount(): Int {
        return NameList.size
    }

    override fun onBindViewHolder(holder: NameAdapter.CustomViewHolder, position: Int) {
        holder.call.setImageResource(NameList.get(position).call)
        holder.person.text = NameList.get(position).name
        holder.time.text = NameList.get(position).time.toString()
    }





    class CustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val call = itemView.findViewById<ImageView>(R.id.callImage)
        val person = itemView.findViewById<TextView>(R.id.personName)
        val time = itemView.findViewById<TextView>(R.id.timeTxt)

    }


}