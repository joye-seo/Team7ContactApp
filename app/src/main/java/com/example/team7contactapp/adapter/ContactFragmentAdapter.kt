package com.example.team7contactapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.team7contactapp.ContactDetailActivity
import com.example.team7contactapp.R
import com.example.team7contactapp.data.MyItem
import com.example.team7contactapp.data.User
import com.example.team7contactapp.databinding.ItemContactBinding


class ContactFragmentAdapter(val mItems: MutableList<MyItem>) : RecyclerView.Adapter<ContactFragmentAdapter.Holder>() {

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(mItems[position])
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItems.size
    }


    inner class Holder(val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MyItem) {
            binding.itemProfile.setImageResource(item.icon ?: R.drawable.profiles)
            binding.itemUserName.text = item.name
            binding.itemFavoriteYellow

            itemView.setOnClickListener {
                val myIntent = Intent(itemView.context, ContactDetailActivity::class.java)
                myIntent.putExtra("Data", User.dataList[adapterPosition])
                itemView.context.startActivity(myIntent)
            }
        }


    }
}