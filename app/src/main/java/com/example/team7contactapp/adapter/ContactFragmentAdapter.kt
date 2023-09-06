package com.example.team7contactapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.team7contactapp.databinding.ItemContactBinding
import com.example.team7contactapp.data.MyItem


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
        holder.itemView.setOnClickListener {  //클릭이벤트추가부분
            itemClick?.onClick(it, position)
        }
        holder.iconImageView.setImageResource(mItems[position].icon)
        holder.name.text = mItems[position].name
        holder.Favorite.setImageResource(mItems[position].favorite)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    inner class Holder(val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {
        val iconImageView = binding.itemProfile
        val name = binding.itemUserName
        val Favorite = binding.itemFavoriteYellow
    }
}