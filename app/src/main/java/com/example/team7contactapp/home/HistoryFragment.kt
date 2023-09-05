package com.example.team7contactapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.team7contactapp.NameAdapter
import com.example.team7contactapp.Names
import com.example.team7contactapp.R
import androidx.fragment.app.Fragment
import com.example.team7contactapp.databinding.FragmentHistoryBinding


class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val namelist = arrayListOf(
            Names(R.drawable.ic_call1,"서수현","오전 1:34"),
            Names(R.drawable.ic_call2,"윤동현","오후 11:57"),
            Names(R.drawable.ic_call3,"황진주","오후 11:20"),
            Names(R.drawable.ic_call2,"이종민","오후 9:57"),
            Names(R.drawable.ic_call3,"김에디","오후 8:44"),
            Names(R.drawable.ic_call1,"정용현","오후 7:52"),
            Names(R.drawable.ic_call2,"이성진","오후 7:07"),
            Names(R.drawable.ic_call1,"서수현","오후 6:45"),
            Names(R.drawable.ic_call1,"이종민","오후 6:20"),
            Names(R.drawable.ic_call3,"김에디","오후 4:17"),
            Names(R.drawable.ic_call2,"황진주","오후 3:02"),
            Names(R.drawable.ic_call3,"윤동현","오후 12:36"),
            Names(R.drawable.ic_call2,"이성진","오전 11:07"),
            Names(R.drawable.ic_call1,"이종민","오전 1:00"),

        )

        binding.rvName.layoutManager = LinearLayoutManager(requireContext() , LinearLayoutManager.VERTICAL,false)
        binding.rvName.setHasFixedSize(true)

        binding.rvName.adapter = NameAdapter(namelist)







    }











    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
