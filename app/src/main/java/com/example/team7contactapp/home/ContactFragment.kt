package com.example.team7contactapp.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.team7contactapp.ContactDialogFragment
import com.example.team7contactapp.adapter.ContactFragmentAdapter
import com.example.team7contactapp.data.ContactManager
import com.example.team7contactapp.data.MyItem
import com.example.team7contactapp.data.User.dataList
import com.example.team7contactapp.databinding.FragmentContactBinding

class ContactFragment : Fragment() {

    private var _binding: FragmentContactBinding? = null //Start
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactBinding.inflate(inflater, container, false)
        val view = binding.root
        return view  //End
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //여기서부터 작업
        //데이터 원본준비
        val adapter = ContactFragmentAdapter(dataList)
        dataList.sortBy { it.name }
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        contactAdd()

        val itemTouchHelperCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    if (direction == ItemTouchHelper.RIGHT) {
                        if (hasCallPermission()) {
                            makeCall(dataList[position].contact)
                        }
                    }
                }
            }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerview)
    }

    private fun hasCallPermission(): Boolean {
        return true
    }

    private fun makeCall(contact: String) {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$contact")
        startActivity(intent)
    }

    private fun contactAdd() {
        binding.btnContactAdd.setOnClickListener {
            loadFragment()
        }
    }

    private fun loadFragment() {
        val fragmentManager = requireActivity().supportFragmentManager
        val newFragment = ContactDialogFragment()
        newFragment.show(fragmentManager, "ContactDialogFragment")
    }
        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }
