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
        binding.tvName.text = testList?.name


        val position = intent.getIntExtra("position", -1)
        val icon = intent.getIntExtra("aIcon", R.id.item_profile)
        val name = intent.getStringExtra("aName")
        val favorite = intent.getIntExtra("aFavorite", R.id.item_favorite_yellow)
    }
}