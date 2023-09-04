package com.example.team7contactapp.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import com.example.team7contactapp.R
import com.example.team7contactapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    private lateinit var contactFragment: ContactFragment
    private lateinit var keypadFragment: KeypadFragment
    private lateinit var myPageFragment: MyPageFragment
    private lateinit var recordFragment: RecordFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        bottomNav()

        //아이템 클릭? 시 선택된 컬러
        binding.bottomNav.itemActiveIndicatorColor = null

    }

    private fun bottomNav() {

        contactFragment = ContactFragment()
        keypadFragment = KeypadFragment()
        myPageFragment = MyPageFragment()
        recordFragment = RecordFragment()

        binding.bottomNav.run {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.btn_contact -> changeFragment(contactFragment)
                    R.id.btn_keypad -> changeFragment(keypadFragment)
                    R.id.btn_record -> changeFragment(recordFragment)
                    R.id.btn_mypage -> changeFragment(myPageFragment)
                }
                true
            }
            selectedItemId = R.id.btn_keypad
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.layoutFragment.id, fragment)
            .commit()
    }
}