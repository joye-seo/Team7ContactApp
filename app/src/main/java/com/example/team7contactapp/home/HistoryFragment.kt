package com.example.team7contactapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.team7contactapp.adapter.NameAdapter
import com.example.team7contactapp.data.Names
import com.example.team7contactapp.R
import com.example.team7contactapp.databinding.FragmentHistoryBinding
import java.time.LocalDate


class HistoryFragment : Fragment() , NameAdapter.NameItemClickListener {

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
        val oneDayAgo = LocalDate.now().minusDays(1)


        val namelist = arrayListOf(
            Names(R.drawable.ic_call1,"서수현 (2)","오전 1:34","휴대전화 010-4565-2156","영상전화, 4분15초"),
            Names(R.drawable.ic_call2,"윤동현","오후 11:57","휴대전화 010-7207-3655","부재중 전화"),
            Names(R.drawable.ic_call3,"황진주","오후 11:20","휴대전화 010-9091-4358","수신전화, 6분31초"),
            Names(R.drawable.ic_call2,"02-6975-9321","오후 9:57","부재중 전화","연락처에 추가"),
            Names(R.drawable.ic_call3,"김에디 (7)","오후 8:44","휴대전화 010-2012-2365","수신전화, 8분51초"),
            Names(R.drawable.ic_call1,"정용현","오후 7:52","휴대전화 010-3054-7512","영상전화, 9분40초"),
            Names(R.drawable.ic_call2,"070-7739-2800","오후 7:07","부재중 전화","연락처에 추가" ),
            Names(R.drawable.ic_call1,"서수현","오후 6:45","휴대전화 010-4565-2156","영상전화, 20분12초" ),
            Names(R.drawable.ic_call1,"이종민 (4)","오후 6:20","휴대전화 010-9117-1065","영상전화, 23분36초" ),
            Names(R.drawable.ic_call3,"김에디","오후 4:17","휴대전화 010-2012-2365","수신전화, 35분45초"),
            Names(R.drawable.ic_call2,"황진주","오후 3:02","휴대전화 010-9091-4358","부재중 전화"),
            Names(R.drawable.ic_call3,"윤동현 (2)","오후 12:36","휴대전화 010-7207-3655","수신전화, 5분02초"),
            Names(R.drawable.ic_call2,"이성진","오전 11:07","휴대전화 010-4659-8951","부재중 전화"),
            Names(R.drawable.ic_call1,"이종민","오전 1:00","휴대전화 010-9117-1065","영상전화, 7분11초"),

            )

        binding.rvName.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvName.setHasFixedSize(true)

        binding.rvName.adapter = NameAdapter(namelist,this)


    }


    override fun onItemClick(name : Names, position : Int) {

    }












    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
