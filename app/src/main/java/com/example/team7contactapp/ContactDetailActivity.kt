package com.example.team7contactapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.widget.ImageView
import android.widget.TextView
import com.example.team7contactapp.databinding.ActivityContactDetailBinding

class ContactDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityContactDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val position = intent.getIntExtra("position", -1)
        val icon = intent.getIntExtra("aIcon", R.id.item_profile)
        val name = intent.getStringExtra("aName")
        val favorite = intent.getIntExtra("aFavorite", R.id.item_favorite_yellow)
    }
}