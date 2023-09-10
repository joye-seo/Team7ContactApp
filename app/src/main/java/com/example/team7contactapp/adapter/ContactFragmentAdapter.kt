package com.example.team7contactapp.adapter

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.ListFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.team7contactapp.ContactDetailActivity
import com.example.team7contactapp.R
import com.example.team7contactapp.data.MyItem
import com.example.team7contactapp.data.User
import com.example.team7contactapp.databinding.ItemContactBinding
import com.example.team7contactapp.databinding.ItemContactViewGridBinding


class ContactFragmentAdapter(var mItems: MutableList<MyItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val LINEAR_VIEW = 1
    private val GRID_VIEW = 2
    private var isGridLayout = true

    fun addList(contact: MyItem) {
        mItems.add(contact)
        mItems.sortBy { it.name }
        notifyDataSetChanged()
    }

    fun deleteList(position: Int) {
        mItems.removeAt(position)
        notifyDataSetChanged()
    }

    fun switchLayout(isGrid: Boolean) {
        isGridLayout = isGrid
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {

            LINEAR_VIEW -> {
                val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HolderLinear(binding)
            }

            GRID_VIEW -> {
                val binding = ItemContactViewGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HolderGrid(binding)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            LINEAR_VIEW -> {
                val item = mItems[position]
                val viewHolderType1 = holder as HolderLinear
                if (mItems[position].favorite) {
                    holder.binding.itemFavoriteYellow.setImageResource(R.drawable.img_bookmarkon)
                } else {
                    holder.binding.itemFavoriteYellow.setImageResource(R.drawable.staroff)
                }
                viewHolderType1.bind(item)
            }

            GRID_VIEW -> {
                val item = mItems[position]
                val viewHolderType2 = holder as HolderGrid
                if (mItems[position].favorite) {
                    holder.binding.ivStar.setImageResource(R.drawable.img_bookmarkon)
                } else {
                    holder.binding.ivStar.setImageResource(R.drawable.staroff)
                }
                viewHolderType2.bind(item)
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (isGridLayout) GRID_VIEW else LINEAR_VIEW
    }

    inner class HolderLinear(val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item_linear: MyItem) {
            isGridLayout = false

            binding.itemProfile.setImageResource(item_linear.icon ?: R.drawable.profiles)
            binding.itemUserName.text = item_linear.name
            binding.itemFavoriteYellow.setOnClickListener {
                if (!item_linear.favorite) {
                    binding.itemFavoriteYellow.setImageResource(R.drawable.img_bookmarkon)
                    item_linear.favorite = true
                    val changedUser = mItems[adapterPosition].copy(favorite = true)
                    mItems.removeAt(adapterPosition)
                    val index = mItems.indexOfFirst { !it.favorite }
                    if (index != -1) {
                        mItems.add(index, changedUser)
                    } else {
                        mItems.add(0, changedUser)
                    }
                    mItems.sortWith(compareBy({ !it.favorite }, { it.name }))
                    notifyDataSetChanged()
                } else {
                    binding.itemFavoriteYellow.setImageResource(R.drawable.staroff)
                    item_linear.favorite = false
                    val changedUser = item_linear.copy(favorite = false)
                    mItems.removeAt(adapterPosition)
                    mItems.add(changedUser)
                    mItems.sortWith(compareBy({ !it.favorite }, { it.name }))
                    notifyDataSetChanged()
                }
            }

            itemView.setOnClickListener {
                val myIntent = Intent(itemView.context, ContactDetailActivity::class.java)
                myIntent.putExtra("Data", User.dataList[adapterPosition])
                itemView.context.startActivity(myIntent)
            }

            itemView.setOnLongClickListener {
                val builder = AlertDialog.Builder(it.context)
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

    inner class HolderGrid(val binding: ItemContactViewGridBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item_grid: MyItem) {
            isGridLayout = true
            binding.ivProfile.setImageResource(item_grid.icon ?: R.drawable.profiles)
            binding.tvName.text = item_grid.name
            binding.tvNumber.text = item_grid.contact
            binding.ivStar.setOnClickListener {
                if (!item_grid.favorite) {
                    binding.ivStar.setImageResource(R.drawable.img_bookmarkon)
                    item_grid.favorite = true
                    val changedUser = mItems[adapterPosition].copy(favorite = true)
                    mItems.removeAt(adapterPosition)
                    val index = mItems.indexOfFirst { !it.favorite }
                    if (index != -1) {
                        mItems.add(index, changedUser)
                    } else {
                        mItems.add(0, changedUser)
                    }
                    mItems.sortWith(compareBy({ !it.favorite }, { it.name }))
                    notifyDataSetChanged()
                } else {
                    binding.ivStar.setImageResource(R.drawable.staroff)
                    item_grid.favorite = false
                    val changedUser = item_grid.copy(favorite = false)
                    mItems.removeAt(adapterPosition)
                    mItems.add(changedUser)
                    mItems.sortWith(compareBy({ !it.favorite }, { it.name }))
                    notifyDataSetChanged()
                }
            }

            itemView.setOnClickListener {
                val myIntent = Intent(itemView.context, ContactDetailActivity::class.java)
                myIntent.putExtra("Data", User.dataList[adapterPosition])
                itemView.context.startActivity(myIntent)
            }

            itemView.setOnLongClickListener {
                val builder = AlertDialog.Builder(it.context)
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