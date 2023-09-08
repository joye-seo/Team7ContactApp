package com.example.team7contactapp.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.team7contactapp.ContactDetailActivity
import com.example.team7contactapp.R
import com.example.team7contactapp.data.MyItem
import com.example.team7contactapp.data.User
import com.example.team7contactapp.databinding.ItemContactBinding


class ContactFragmentAdapter(var mItems: MutableList<MyItem>) : RecyclerView.Adapter<ContactFragmentAdapter.Holder>() {

    fun addList(contact: MyItem) {
        mItems.add(contact)
        mItems.sortBy { it.name }
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        Log.d("bind11112222", "")
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(mItems[position])
        Log.d("bind1111", position.toString())
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }


    inner class Holder(val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MyItem) {
            binding.itemProfile.setImageResource(item.icon ?: R.drawable.profiles)
            binding.itemUserName.text = item.name
            Log.d("bind111111111", item.name.toString())
            binding.itemFavoriteYellow

            itemView.setOnClickListener {
                val myIntent = Intent(itemView.context, ContactDetailActivity::class.java)
                myIntent.putExtra("Data", User.dataList[adapterPosition])
                itemView.context.startActivity(myIntent)
            }
            itemView.setOnLongClickListener {

                true
            }
        }


    }
}