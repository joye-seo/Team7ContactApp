package com.example.team7contactapp.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.team7contactapp.R
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
        val dataList = mutableListOf<MyItem>()
        dataList.add(MyItem(R.drawable.donghyun, "윤동현", R.drawable.img_bookmarkon))
        dataList.add(MyItem(R.drawable.jinjoo, "황진주", R.drawable.img_bookmarkoff))
        dataList.add(MyItem(R.drawable.profiles, "서수현", R.drawable.img_bookmarkon))
        dataList.add(MyItem(R.drawable.profiles, "이종민", R.drawable.img_bookmarkon))
        dataList.add(MyItem(R.drawable.profiles, "이성진", R.drawable.img_bookmarkoff))
        dataList.add(MyItem(R.drawable.profiles, "최다현", R.drawable.img_bookmarkon))
        dataList.add(MyItem(R.drawable.profiles, "정용현", R.drawable.img_bookmarkoff))
        dataList.add(MyItem(R.drawable.profiles, "김에디", R.drawable.img_bookmarkon))


        val adapter = ContactFragmentAdapter(dataList)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(context)


    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}