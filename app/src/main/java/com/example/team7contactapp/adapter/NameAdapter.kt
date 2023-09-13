package com.example.team7contactapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.team7contactapp.data.Names
import com.example.team7contactapp.R

class NameAdapter(
    private val NameList : ArrayList<Names>,
    private val listener : NameItemClickListener
    ) :  RecyclerView.Adapter<NameAdapter.CustomViewHolder>()
{


    interface NameItemClickListener {
        fun  onItemClick(name : Names, position: Int)
    }

    private  var expandedPosition = -1



    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): CustomViewHolder {

        val  view = LayoutInflater.from(parent.context).inflate(R.layout.adrees_item,parent,false)
        return CustomViewHolder(view)

    }

    override fun getItemCount(): Int {
        return NameList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currentItem = NameList[position]

        // 아이템의 상태를 기반으로 가시성 설정
        holder.phoneNumber.visibility = if (currentItem.isExpanded) View.VISIBLE else View.GONE
        holder.infoTxt.visibility = if (currentItem.isExpanded) View.VISIBLE else View.GONE


        // 항목의 내용을 Names 객체의 데이터로 설정
        holder.phoneNumber.text = currentItem.phoneNumber  // 전화번호 설정
        holder.infoTxt.text = currentItem.recode           // recode 설정

        holder.call.setImageResource(currentItem.call)
        holder.person.text = currentItem.name
        holder.time.text = currentItem.time

        holder.itemView.setOnClickListener {

            if (expandedPosition >= 0 && expandedPosition != position){
                NameList[expandedPosition].isExpanded = false
                notifyItemChanged(expandedPosition)
            }
            currentItem.isExpanded = !currentItem.isExpanded  // 아이템 상태 토글
            notifyItemChanged(position)  // 해당 아이템이 변경되었음을 알림

            listener.onItemClick(currentItem, position)

            expandedPosition = if (currentItem.isExpanded) position else -1
        }
    }






    class CustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val call = itemView.findViewById<ImageView>(R.id.callImage)
        val person = itemView.findViewById<TextView>(R.id.personName)
        val time = itemView.findViewById<TextView>(R.id.timeTxt)
        val phoneNumber = itemView.findViewById<TextView>(R.id.phoneNumber)
        val infoTxt = itemView.findViewById<TextView>(R.id.infoTxt)


    }


}