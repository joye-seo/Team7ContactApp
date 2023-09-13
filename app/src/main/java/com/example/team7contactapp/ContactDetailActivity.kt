package com.example.team7contactapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.team7contactapp.data.MyItem
import com.example.team7contactapp.databinding.ActivityContactDetailBinding
import com.example.team7contactapp.home.HomeActivity

class ContactDetailActivity : AppCompatActivity() {

    var isLike = false

    private lateinit var binding: ActivityContactDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityContactDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.ivPen.setOnClickListener {
            val intent = Intent(this, DetailModityActivity::class.java)
            startActivity(intent)
        }



        //parcelable 데이터를 받아올 때 사용하는 코드
        var testList = intent.getParcelableExtra<MyItem>("Data")

        //DetailActivity 와 parcelable데이터를 연결시켜줌
        binding.ivProfile.setImageResource(testList?.icon ?: R.drawable.profiles)
        binding.tvNameinfo.text = testList?.name
        binding.tvContactinfo.text = testList?.contact
        binding.tvMailinfo.text = testList?.emaill
        binding.tvBirthinfo.text = testList?.birth
        binding.tvAddressinfo.text = testList?.address
        binding.tvMemoinfo.text = testList?.memo

        if (testList?.favorite == true) {
            binding.activityContactDetailStar.setImageResource(R.drawable.img_bookmarkon)
            isLike = true
        } else {
            binding.activityContactDetailStar.setImageResource(R.drawable.staroff)
            isLike = false
        }

        binding.ivCall.setOnClickListener {
            makeCall(testList?.contact!!)
        }

        binding.icBack.setOnClickListener {
            exit()
        }
    }

    private fun makeCall(contact: String) {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$contact")
        startActivity(intent)
    }

    fun exit() {
        val intent = Intent(this, HomeActivity::class.java).apply {
            putExtra("isLike", isLike)
        }
        setResult(RESULT_OK, intent)
        if (!isFinishing) finish()
    }
}