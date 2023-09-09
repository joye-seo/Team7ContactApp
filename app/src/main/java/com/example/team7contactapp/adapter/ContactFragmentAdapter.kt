package com.example.team7contactapp.adapter

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.team7contactapp.ContactDetailActivity
import com.example.team7contactapp.R
import com.example.team7contactapp.data.MyItem
import com.example.team7contactapp.data.User
import com.example.team7contactapp.databinding.ItemContactBinding


class ContactFragmentAdapter(var mItems: MutableList<MyItem> ) :
    RecyclerView.Adapter<ContactFragmentAdapter.Holder>() {

    fun addList(contact: MyItem) {
        mItems.add(contact)
        mItems.sortBy { it.name }
        notifyDataSetChanged()
    }

    fun deleteList(position: Int) {
        mItems.removeAt(position)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(mItems[position])

        if (mItems[position].favorite) {
            holder.binding.itemFavoriteYellow.setImageResource(R.drawable.img_bookmarkon)
        } else {
            holder.binding.itemFavoriteYellow.setImageResource(R.drawable.staroff)
        }
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
            binding.itemFavoriteYellow.setOnClickListener {

                if (!item.favorite) {
                    binding.itemFavoriteYellow.setImageResource(R.drawable.img_bookmarkon)
                    item.favorite = true
                    val item = mItems[adapterPosition]
                    mItems.removeAt(adapterPosition)
                    mItems.add(0, item)
                    notifyItemMoved(adapterPosition, 0)
                } else {
                    binding.itemFavoriteYellow.setImageResource(R.drawable.staroff)
                    item.favorite = false
                    mItems.sortBy { it.name }
                    notifyDataSetChanged()
                }

            }

            itemView.setOnClickListener {
                val myIntent = Intent(itemView.context, ContactDetailActivity::class.java)
                myIntent.putExtra("Data", User.dataList[adapterPosition])
                itemView.context.startActivity(myIntent)
            }

            itemView.setOnLongClickListener {
                var builder = AlertDialog.Builder(it.context)
                builder.setTitle("연락처 삭제")
                builder.setMessage("정말로 삭제하시겠습니까?!?!?!?!?!!")

                val listener = object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        when (which) {
                            DialogInterface.BUTTON_POSITIVE ->
                                deleteList(adapterPosition)
                        }

                    }

                }
                builder.setPositiveButton("삭제", listener)
                builder.setNegativeButton("취소", listener)

                builder.show()
                true
            }
        }


    }
}