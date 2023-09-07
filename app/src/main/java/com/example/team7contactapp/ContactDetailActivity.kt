package com.example.team7contactapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.team7contactapp.data.MyItem
import com.example.team7contactapp.databinding.ActivityContactDetailBinding

class ContactDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityContactDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //parcelable 데이터를 받아올 때 사용하는 코드
        var testList = intent.getParcelableExtra<MyItem>("Data")

        //DetailActivity 와 parcelable데이터를 연결시켜줌
        binding.ivProfile.setImageResource(testList?.icon?: R.drawable.profiles)
        binding.tvNameinfo.text = testList?.name
        binding.tvContactinfo.text = testList?.contact
        binding.tvBirthinfo.text = testList?.birth
        binding.tvAddressinfo.text = testList?.address
        binding.tvMemoinfo.text = testList?.memo

    }
}