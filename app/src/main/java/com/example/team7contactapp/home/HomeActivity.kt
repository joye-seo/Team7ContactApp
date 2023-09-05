package com.example.team7contactapp.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.team7contactapp.R
import com.example.team7contactapp.adapter.HomeViewPagerAdapter
import com.example.team7contactapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    private lateinit var mHomeViewPagerAdapter: HomeViewPagerAdapter
    private lateinit var contactFragment: ContactFragment
    private lateinit var keypadFragment: KeypadFragment
    private lateinit var myPageFragment: MyPageFragment
    private lateinit var recordFragment: RecordFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViewPager()
        initBottomNav()

    }

    private fun initViewPager() = with(binding) {
        mHomeViewPagerAdapter = HomeViewPagerAdapter(this@HomeActivity)
        viewpagerHome.adapter = mHomeViewPagerAdapter
        viewpagerHome.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bottomNav.menu.getItem(position).isChecked = true
            }
        })
    }

    private fun initBottomNav() = with(binding) {
        contactFragment = ContactFragment()
        keypadFragment = KeypadFragment()
        myPageFragment = MyPageFragment()
        recordFragment = RecordFragment()

        binding.bottomNav.run {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.btn_contact -> viewpagerHome.currentItem = 0
                    R.id.btn_keypad -> viewpagerHome.currentItem = 1
                    R.id.btn_record -> viewpagerHome.currentItem = 2
                    R.id.btn_mypage -> viewpagerHome.currentItem = 3
                }
                true
            }
            selectedItemId = R.id.btn_keypad
        }
        //아이템 클릭? 시 선택된 컬러
//        binding.bottomNav.itemActiveIndicatorColor = null
    }

}