package com.example.team7contactapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.team7contactapp.data.MyItem
import com.example.team7contactapp.databinding.ActivityContactDetailBinding

class ContactDetailActivity : AppCompatActivity() {

    private var isLike = false

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
        binding.tvMailinfo.text = testList?.emaill
        binding.tvBirthinfo.text = testList?.birth
        binding.tvAddressinfo.text = testList?.address
        binding.tvMemoinfo.text = testList?.memo

//        binding.activityContactDetailStar.setImageResource(if (isLike) {R.drawable.img_bookmarkon}else{R.drawable.img_bookmarkoff})

        binding.activityContactDetailStar.setOnClickListener{
//        isLike 를 전역변수로 선언한뒤  true면 노란별이 false면 하얀별이 들어오도록 설정
            if(!isLike){
                binding.activityContactDetailStar.setImageResource(R.drawable.img_bookmarkon)
                isLike = true
            }
            else{
                binding.activityContactDetailStar.setImageResource(R.drawable.img_bookmarkoff)
                isLike = false
            }

        }

    }
}