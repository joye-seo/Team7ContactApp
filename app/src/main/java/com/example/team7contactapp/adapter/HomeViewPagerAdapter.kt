package com.example.team7contactapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.team7contactapp.home.ContactFragment
import com.example.team7contactapp.home.KeypadFragment
import com.example.team7contactapp.home.MyPageFragment
import com.example.team7contactapp.home.RecordFragment

class HomeViewPagerAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ContactFragment()
            1 -> KeypadFragment()
            2 -> RecordFragment()
            else -> MyPageFragment()
        }
    }
}